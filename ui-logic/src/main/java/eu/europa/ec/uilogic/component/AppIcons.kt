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

package eu.europa.ec.uilogic.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.vector.ImageVector
import eu.europa.ec.resourceslogic.R

/**
 * Data class to be used when we want to display an Icon.
 * @param resourceId The id of the icon. Can be null
 * @param contentDescriptionId The id of its content description.
 * @param imageVector The [ImageVector] of the icon, null by default.
 * @throws IllegalArgumentException If both [resourceId] AND [imageVector] are null.
 */
@Stable
data class IconDataUi(
    @param:DrawableRes val resourceId: Int?,
    @param:StringRes val contentDescriptionId: Int,
    val imageVector: ImageVector? = null,
) {
    init {
        require(
            resourceId == null && imageVector != null
                    || resourceId != null && imageVector == null
                    || resourceId != null && imageVector != null
        ) {
            "An Icon should at least have a valid resourceId or a valid imageVector."
        }
    }
}

/**
 * A Singleton object responsible for providing access to all the app's Icons.
 */
object AppIcons {

    val ArrowBack: IconDataUi = IconDataUi(
        resourceId = null,
        contentDescriptionId = R.string.content_description_arrow_back_icon,
        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
    )

    val Close: IconDataUi = IconDataUi(
        resourceId = null,
        contentDescriptionId = R.string.content_description_close_icon,
        imageVector = Icons.Filled.Close
    )

    val VerticalMore: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_more,
        contentDescriptionId = R.string.content_description_more_vert_icon,
        imageVector = null
    )

    val Warning: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_warning,
        contentDescriptionId = R.string.content_description_warning_icon,
        imageVector = null
    )

    val Error: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_error,
        contentDescriptionId = R.string.content_description_error_icon,
        imageVector = null
    )

    val ErrorFilled: IconDataUi = IconDataUi(
        resourceId = null,
        contentDescriptionId = R.string.content_description_error_icon,
        imageVector = Icons.Default.Info
    )

    val Delete: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_delete,
        contentDescriptionId = R.string.content_description_delete_icon,
        imageVector = null
    )

    val TouchId: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_touch_id,
        contentDescriptionId = R.string.content_description_touch_id_icon,
        imageVector = null
    )

    val QR: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_qr,
        contentDescriptionId = R.string.content_description_qr_icon,
        imageVector = null
    )

    val NFC: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_nfc,
        contentDescriptionId = R.string.content_description_nfc_icon,
        imageVector = null
    )

    val User: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_user,
        contentDescriptionId = R.string.content_description_user_icon,
        imageVector = null
    )

    val Id: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_id,
        contentDescriptionId = R.string.content_description_id_icon,
        imageVector = null
    )

    val IdStroke: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_id_stroke,
        contentDescriptionId = R.string.content_description_id_stroke_icon,
        imageVector = null
    )

    val LogoFull: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_logo_full,
        contentDescriptionId = R.string.content_description_logo_full_icon,
        imageVector = null
    )

    val LogoPlain: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_logo_plain,
        contentDescriptionId = R.string.content_description_logo_plain_icon,
        imageVector = null
    )

    val LogoText: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_logo_text,
        contentDescriptionId = R.string.content_description_logo_text_icon,
        imageVector = null
    )

    val KeyboardArrowDown: IconDataUi = IconDataUi(
        resourceId = null,
        contentDescriptionId = R.string.content_description_arrow_down_icon,
        imageVector = Icons.Default.KeyboardArrowDown
    )

    val KeyboardArrowUp: IconDataUi = IconDataUi(
        resourceId = null,
        contentDescriptionId = R.string.content_description_arrow_up_icon,
        imageVector = Icons.Default.KeyboardArrowUp
    )

    val Visibility: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_visibility_on,
        contentDescriptionId = R.string.content_description_visibility_icon,
        imageVector = null
    )

    val VisibilityOff: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_visibility_off,
        contentDescriptionId = R.string.content_description_visibility_off_icon,
        imageVector = null
    )

    val Add: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_add,
        contentDescriptionId = R.string.content_description_add_icon,
        imageVector = null
    )

    val Edit: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_edit,
        contentDescriptionId = R.string.content_description_edit_icon,
        imageVector = null
    )

    val Sign: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_sign_document,
        contentDescriptionId = R.string.content_description_edit_icon,
        imageVector = null
    )

    val QrScanner: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_qr_scanner,
        contentDescriptionId = R.string.content_description_qr_scanner_icon,
        imageVector = null
    )

    val Verified: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_verified,
        contentDescriptionId = R.string.content_description_verified_icon,
        imageVector = null
    )

    val Message: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_message,
        contentDescriptionId = R.string.content_description_message_icon,
        imageVector = null
    )

    val ClockTimer: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_clock_timer,
        contentDescriptionId = R.string.content_description_clock_timer_icon,
        imageVector = null
    )

    val OpenNew: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_open_new,
        contentDescriptionId = R.string.content_description_open_new_icon,
        imageVector = null
    )

    val KeyboardArrowRight: IconDataUi = IconDataUi(
        resourceId = null,
        contentDescriptionId = R.string.content_description_arrow_right_icon,
        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight
    )

    val HandleBar: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_handle_bar,
        contentDescriptionId = R.string.content_description_handle_bar_icon,
        imageVector = null
    )

    val Search: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_search,
        contentDescriptionId = R.string.content_description_search_icon,
        imageVector = null
    )

    val PresentDocumentInPerson: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_present_document_same_device,
        contentDescriptionId = R.string.content_description_present_document_same_device_icon,
        imageVector = null
    )

    val PresentDocumentOnline: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_present_document_cross_device,
        contentDescriptionId = R.string.content_description_present_document_cross_device_icon,
        imageVector = null
    )

    val AddDocumentFromList: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_add_document_from_list,
        contentDescriptionId = R.string.content_description_add_document_from_list_icon,
        imageVector = null
    )

    val AddDocumentFromQr: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_add_document_from_qr,
        contentDescriptionId = R.string.content_description_add_document_from_qr_icon,
        imageVector = null
    )

    val Bookmark: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_bookmark,
        contentDescriptionId = R.string.content_description_bookmark_icon,
        imageVector = null
    )

    val BookmarkFilled: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_bookmark_filled,
        contentDescriptionId = R.string.content_description_bookmark_filled_icon,
        imageVector = null
    )

    val Certified: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_certified,
        contentDescriptionId = R.string.content_description_certified_icon,
        imageVector = null
    )

    val Success: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_success,
        contentDescriptionId = R.string.content_description_success_icon,
        imageVector = null
    )

    val Documents: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_documents,
        contentDescriptionId = R.string.content_description_documents_icon,
        imageVector = null
    )

    val Download: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_download,
        contentDescriptionId = R.string.content_description_download_icon,
        imageVector = null
    )

    val Filters: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_filters,
        contentDescriptionId = R.string.content_description_filters_icon,
        imageVector = null
    )

    val Home: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_home,
        contentDescriptionId = R.string.content_description_home_icon,
        imageVector = null
    )

    val Menu: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_menu,
        contentDescriptionId = R.string.content_description_menu_icon,
        imageVector = null
    )

    val Contract: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_contract,
        contentDescriptionId = R.string.content_description_signature_icon,
        imageVector = null
    )

    val InProgress: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_in_progress,
        contentDescriptionId = R.string.content_description_in_progress_icon,
        imageVector = null
    )

    val Notifications: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_notifications,
        contentDescriptionId = R.string.content_description_notifications_icon,
        imageVector = null
    )

    val Transactions: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_transactions,
        contentDescriptionId = R.string.content_description_transactions_icon,
        imageVector = null
    )

    val WalletActivated: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_wallet_activated,
        contentDescriptionId = R.string.content_description_wallet_activated_icon,
        imageVector = null
    )

    val WalletSecured: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_wallet_secured,
        contentDescriptionId = R.string.content_description_wallet_secured_icon,
        imageVector = null
    )

    val Info: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_info,
        contentDescriptionId = R.string.content_description_info_icon,
        imageVector = null
    )

    val IdCards: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_authenticate_id_cards,
        contentDescriptionId = R.string.content_description_issuer_icon,
        imageVector = null
    )

    val SignDocumentFromDevice: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_sign_document_from_device,
        contentDescriptionId = R.string.content_description_add_document_from_list_icon,
        imageVector = null
    )

    val SignDocumentFromQr: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_sign_document_from_qr,
        contentDescriptionId = R.string.content_description_add_document_from_qr_icon,
        imageVector = null
    )

    val ChangePin: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_change_pin,
        contentDescriptionId = R.string.content_description_change_pin_icon,
        imageVector = null
    )

    val Check: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_check,
        contentDescriptionId = R.string.content_description_check_icon,
        imageVector = null
    )

    val OpenInBrowser: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_open_in_browser,
        contentDescriptionId = R.string.content_description_open_in_browser_icon,
        imageVector = null
    )

    val DateRange: IconDataUi = IconDataUi(
        resourceId = null,
        contentDescriptionId = R.string.content_description_date_range_icon,
        imageVector = Icons.Default.DateRange
    )

    val Settings: IconDataUi = IconDataUi(
        resourceId = R.drawable.ic_settings,
        contentDescriptionId = R.string.content_description_settings_icon,
        imageVector = null
    )
}