/*
 * Copyright (c) 2023 European Commission
 *
 * Licensed under the EUPL, Version 1.2 or - as soon they will be approved by the European
 * Commission - subsequent versions of the EUPL (the "Licence"); You may not use this work
 * except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 * https://joinup.ec.europa.eu/software/page/eupl
 *
 * Unless required by applicable law or agreed to in writing, software distributed under
 * the Licence is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF
 * ANY KIND, either express or implied. See the Licence for the specific language
 * governing permissions and limitations under the Licence.
 */

package eu.europa.ec.commonfeature.ui.document_details.transformer

import eu.europa.ec.businesslogic.util.formatInstant
import eu.europa.ec.commonfeature.model.DocumentDetailsUi
import eu.europa.ec.commonfeature.model.DocumentUiIssuanceState
import eu.europa.ec.commonfeature.ui.document_details.domain.DocumentDetailsDomain
import eu.europa.ec.commonfeature.ui.document_details.domain.DocumentItem
import eu.europa.ec.commonfeature.util.documentHasExpired
import eu.europa.ec.commonfeature.util.generateUniqueFieldId
import eu.europa.ec.commonfeature.util.keyIsPortrait
import eu.europa.ec.commonfeature.util.keyIsSignature
import eu.europa.ec.commonfeature.util.parseKeyValueUi
import eu.europa.ec.corelogic.extension.getLocalizedClaimName
import eu.europa.ec.corelogic.model.toDocumentIdentifier
import eu.europa.ec.eudi.wallet.document.IssuedDocument
import eu.europa.ec.resourceslogic.provider.ResourceProvider
import eu.europa.ec.uilogic.component.ListItemData
import eu.europa.ec.uilogic.component.ListItemLeadingContentData
import eu.europa.ec.uilogic.component.ListItemMainContentData

object DocumentDetailsTransformer {

    fun transformToDocumentDetailsDomain(
        document: IssuedDocument,
        resourceProvider: ResourceProvider
    ): Result<DocumentDetailsDomain> = runCatching {
        val userLocale = resourceProvider.getLocale()

        val detailsDocumentItems = document.data.claims
            .map { claim ->
                val displayKey: String = claim.metadata?.display.getLocalizedClaimName(
                    userLocale = userLocale,
                    fallback = claim.identifier
                )

                transformToDocumentDetailsDocumentItem(
                    displayKey = displayKey,
                    key = claim.identifier,
                    item = claim.value ?: "",
                    resourceProvider = resourceProvider,
                    documentId = document.id
                )
            }

        val docHasExpired = documentHasExpired(document.validUntil)

        return@runCatching DocumentDetailsDomain(
            docName = document.name,
            docId = document.id,
            documentIdentifier = document.toDocumentIdentifier(),
            documentExpirationDateFormatted = document.validUntil.formatInstant(),
            documentHasExpired = docHasExpired,
            detailsItems = detailsDocumentItems
        )
    }

    fun DocumentDetailsDomain.transformToDocumentDetailsUi(): DocumentDetailsUi {
        val documentDetailsListItemData = this.detailsItems.toListItemData()
        return DocumentDetailsUi(
            documentId = this.docId,
            documentName = this.docName,
            documentIdentifier = this.documentIdentifier,
            documentExpirationDateFormatted = this.documentExpirationDateFormatted,
            documentHasExpired = this.documentHasExpired,
            documentDetails = documentDetailsListItemData,
            documentIssuanceState = DocumentUiIssuanceState.Issued,
        )
    }

    fun List<DocumentItem>.toListItemData(): List<ListItemData> {
        return this
            .sortedBy { it.readableName.lowercase() }
            .map {

                val mainContent = when {
                    keyIsPortrait(key = it.elementIdentifier) -> {
                        ListItemMainContentData.Text(text = "")
                    }

                    keyIsSignature(key = it.elementIdentifier) -> {
                        ListItemMainContentData.Image(base64Image = it.value)
                    }

                    else -> {
                        ListItemMainContentData.Text(text = it.value)
                    }
                }

                val itemId = generateUniqueFieldId(
                    elementIdentifier = it.elementIdentifier,
                    documentId = it.docId
                )

                val leadingContent = if (keyIsPortrait(key = it.elementIdentifier)) {
                    ListItemLeadingContentData.UserImage(userBase64Image = it.value)
                } else {
                    null
                }

                ListItemData(
                    itemId = itemId,
                    mainContentData = mainContent,
                    overlineText = it.readableName,
                    leadingContentData = leadingContent
                )
            }
    }
}

fun transformToDocumentDetailsDocumentItem(
    key: String,
    displayKey: String,
    item: Any,
    resourceProvider: ResourceProvider,
    documentId: String
): DocumentItem {

    val values = StringBuilder()
    val localizedKey = displayKey.ifEmpty { key }

    parseKeyValueUi(
        item = item,
        groupIdentifier = localizedKey,
        groupIdentifierKey = key,
        resourceProvider = resourceProvider,
        allItems = values
    )
    val groupedValues = values.toString()

    return DocumentItem(
        elementIdentifier = key,
        value = groupedValues,
        readableName = localizedKey,
        docId = documentId
    )
}