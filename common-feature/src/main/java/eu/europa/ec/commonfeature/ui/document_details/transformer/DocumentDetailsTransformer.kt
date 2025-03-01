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

import eu.europa.ec.businesslogic.extension.compareLocaleLanguage
import eu.europa.ec.businesslogic.util.toDateFormatted
import eu.europa.ec.commonfeature.model.DocumentUi
import eu.europa.ec.commonfeature.model.DocumentUiIssuanceState
import eu.europa.ec.commonfeature.ui.document_details.model.DocumentDetailsUi
import eu.europa.ec.commonfeature.ui.document_details.model.DocumentJsonKeys
import eu.europa.ec.commonfeature.util.documentHasExpired
import eu.europa.ec.commonfeature.util.extractFullNameFromDocumentOrEmpty
import eu.europa.ec.commonfeature.util.extractValueFromDocumentOrEmpty
import eu.europa.ec.commonfeature.util.parseKeyValueUi
import eu.europa.ec.corelogic.model.toDocumentIdentifier
import eu.europa.ec.eudi.wallet.document.IssuedDocument
import eu.europa.ec.resourceslogic.R
import eu.europa.ec.resourceslogic.provider.ResourceProvider
import eu.europa.ec.uilogic.component.InfoTextWithNameAndImageData
import eu.europa.ec.uilogic.component.InfoTextWithNameAndValueData

object DocumentDetailsTransformer {

    fun transformToUiItem(
        document: IssuedDocument,
        resourceProvider: ResourceProvider,
    ): DocumentUi? {

        val documentIdentifierUi = document.toDocumentIdentifier()

        val detailsItems = document.data.claims
            .map { claim ->
                transformToDocumentDetailsUi(
                    displayKey = claim.metadata?.display?.firstOrNull {
                        resourceProvider.getLocale().compareLocaleLanguage(it.locale)
                    }?.name,
                    key = claim.identifier,
                    item = claim.value ?: "",
                    resourceProvider = resourceProvider
                )
            }

        val documentImage = extractValueFromDocumentOrEmpty(
            document = document,
            key = DocumentJsonKeys.PORTRAIT
        )

        val documentExpirationDate = extractValueFromDocumentOrEmpty(
            document = document,
            key = DocumentJsonKeys.EXPIRY_DATE
        )

        val docHasExpired = documentHasExpired(documentExpirationDate)

        return DocumentUi(
            documentId = document.id,
            documentName = document.name,
            documentIdentifier = documentIdentifierUi,
            documentExpirationDateFormatted = documentExpirationDate.toDateFormatted().orEmpty(),
            documentHasExpired = docHasExpired,
            documentImage = documentImage,
            documentDetails = detailsItems,
            userFullName = extractFullNameFromDocumentOrEmpty(document),
            documentIssuanceState = DocumentUiIssuanceState.Issued,
        )
    }

}

private fun transformToDocumentDetailsUi(
    key: String,
    displayKey: String?,
    item: Any,
    resourceProvider: ResourceProvider
): DocumentDetailsUi {

    val values = StringBuilder()
    val localizedKey = displayKey ?: key


    parseKeyValueUi(
        item = item,
        groupIdentifier = localizedKey,
        groupIdentifierKey = key,
        resourceProvider = resourceProvider,
        allItems = values
    )
    val groupedValues = values.toString()

    return when (key) {
        DocumentJsonKeys.SIGNATURE -> {
            DocumentDetailsUi.SignatureItem(
                itemData = InfoTextWithNameAndImageData(
                    title = localizedKey,
                    base64Image = groupedValues
                )
            )
        }

        DocumentJsonKeys.PORTRAIT -> {
            DocumentDetailsUi.DefaultItem(
                itemData = InfoTextWithNameAndValueData.create(
                    title = localizedKey,
                    infoValues = arrayOf(resourceProvider.getString(R.string.document_details_portrait_readable_identifier))
                )
            )
        }

        else -> {
            DocumentDetailsUi.DefaultItem(
                itemData = InfoTextWithNameAndValueData.create(
                    title = localizedKey,
                    infoValues = arrayOf(groupedValues)
                )
            )
        }
    }
}