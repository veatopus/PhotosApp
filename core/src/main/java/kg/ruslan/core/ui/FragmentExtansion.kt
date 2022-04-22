package kg.ruslan.core.ui

import androidx.fragment.app.Fragment

fun Fragment.showErrorAlert(rTitle: String = "Error", rMessage: String? = null, retryOnPositiveButton: (() -> Unit)? = null) {
    alertDialog {
        title = rTitle
        if (rMessage != null)
            message = rMessage
        positiveButton {
            retryOnPositiveButton?.invoke()
        }
    }
}