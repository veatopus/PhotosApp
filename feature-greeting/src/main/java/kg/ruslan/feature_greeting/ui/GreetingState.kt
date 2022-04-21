package kg.ruslan.feature_greeting.ui

import android.net.Uri
import kg.ruslan.core.models.Candidate

data class GreetingState(
    val isLoading: Boolean = false,
    val shouldOpenHomeScreen: Boolean = false,
    val data: Candidate? = null,
    val dataNotFound: Boolean = false,
    val openImageInScreen: Uri? = null
)

sealed class GreetingUIIntents {
    data class PhotoClicked(val uriOfImage: Uri): GreetingUIIntents()
    object GetCandidate : GreetingUIIntents()
    object NextButtonClicked : GreetingUIIntents()
}