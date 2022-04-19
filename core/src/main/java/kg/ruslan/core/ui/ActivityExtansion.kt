package kg.ruslan.core.ui

import android.app.Activity
import android.content.Context

/**
 * Extension variable to use [DialogLoading] in your Activity by means of extension functions
 */
private val Activity.progressDialog: DialogLoading by lazy {
    DialogLoading()
}

/**
 * Extension function to show custom progress dialog in this Activity
 * It's using private extension variable [progressDialog] to display dialog
 * and having ability to dismiss it out of this func
 * @param title is a custom title witch can be displayed with progress
 * to transmit some information to user
 */
fun Activity.showProgress(title: String? = null, context: Context = this) {
    progressDialog.dismissIfOpen()
    progressDialog.show(context, title)
}

/**
 * Extension function to dismiss private extension variable [progressDialog]
 * It's closing progress dialog
 */
fun Activity.dismissProgress() {
    progressDialog.dismissDialog()
}