/*
 *
 *  * Copyright (c) 2023 European Commission
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package eu.europa.ec.presentationfeature.interactor

import eu.europa.ec.businesslogic.extension.safeAsync
import eu.europa.ec.commonfeature.model.DocumentTypeUi
import eu.europa.ec.commonfeature.ui.request.model.UserDataDomain
import eu.europa.ec.commonfeature.ui.request.model.UserIdentificationDomain
import eu.europa.ec.resourceslogic.provider.ResourceProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

sealed class PresentationSameDeviceInteractorPartialState {
    data class Success(val userDataDomain: UserDataDomain) :
        PresentationSameDeviceInteractorPartialState()

    data class Failure(val error: String) : PresentationSameDeviceInteractorPartialState()
}

interface PresentationSameDeviceInteractor {
    fun getUserData(): Flow<PresentationSameDeviceInteractorPartialState>
}

class PresentationSameDeviceInteractorImpl(
    private val resourceProvider: ResourceProvider,
) : PresentationSameDeviceInteractor {

    private val genericErrorMsg
        get() = resourceProvider.genericErrorMessage()

    override fun getUserData(): Flow<PresentationSameDeviceInteractorPartialState> =
        flow {
            delay(2_000L)
            emit(
                PresentationSameDeviceInteractorPartialState.Success(
                    userDataDomain = getFakeUserData()
                )
            )
        }.safeAsync {
            PresentationSameDeviceInteractorPartialState.Failure(
                error = it.localizedMessage ?: genericErrorMsg
            )
        }

    private fun getFakeUserData(): UserDataDomain {
        return UserDataDomain(
            documentTypeUi = DocumentTypeUi.DIGITAL_ID,
            optionalFields = listOf(
                UserIdentificationDomain(
                    name = "Registration ID",
                    value = "EUDI123456"
                ),
                UserIdentificationDomain(
                    name = "Family Name",
                    value = "Doe"
                ),
                UserIdentificationDomain(
                    name = "First Name",
                    value = "Jane"
                ),
                UserIdentificationDomain(
                    name = "Room Number",
                    value = "A2"
                ),
                UserIdentificationDomain(
                    name = "Seat Number",
                    value = "128"
                ),
                UserIdentificationDomain(
                    name = "Registration ID",
                    value = "EUDI123456"
                ),
                UserIdentificationDomain(
                    name = "Family Name",
                    value = "Doe"
                ),
                UserIdentificationDomain(
                    name = "First Name",
                    value = "Jane"
                ),
                UserIdentificationDomain(
                    name = "Room Number",
                    value = "A2"
                ),
                UserIdentificationDomain(
                    name = "Seat Number",
                    value = "128"
                )
            ),
            requiredFieldsTitle = "Verification Data",
            requiredFields = listOf(
                UserIdentificationDomain(
                    name = "Issuance date",
                    value = null
                ),
                UserIdentificationDomain(
                    name = "Expiration date",
                    value = null
                ),
                UserIdentificationDomain(
                    name = "Country of issuance",
                    value = null
                ),
                UserIdentificationDomain(
                    name = "Issuing authority",
                    value = null
                )
            )
        )
    }
}