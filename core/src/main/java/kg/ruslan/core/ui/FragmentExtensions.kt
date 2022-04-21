package kg.ruslan.core.ui

import androidx.fragment.app.Fragment

fun Fragment.showProgress(title: String? = null) {
    requireActivity().showProgress(title = title)
}

fun Fragment.dismissProgress() = requireActivity().dismissProgress()