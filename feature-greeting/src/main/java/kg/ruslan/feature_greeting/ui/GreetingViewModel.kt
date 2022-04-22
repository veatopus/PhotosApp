package kg.ruslan.feature_greeting.ui

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.ruslan.core.resource.Resource
import kg.ruslan.domain.use_cases.GetCandidateUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class GreetingViewModel(
    val getCandidateUseCase: GetCandidateUseCase
): ViewModel() {

    private var _state: MutableStateFlow<GreetingState> =
        MutableStateFlow(GreetingState())
    var state: StateFlow<GreetingState> = _state

    val handleSideEffects: (GreetingUIIntents) -> Unit

    init {
        handleSideEffects = { intent ->
            viewModelScope.launch(Dispatchers.IO) {
                handleIntent(intent = intent)
            }
        }
    }

    private suspend fun handleIntent(intent: GreetingUIIntents) {
        when(intent) {
            is GreetingUIIntents.GetCandidate -> getCandidate()
            is GreetingUIIntents.PhotoClicked -> showPhoto(intent.uriOfImage)
            is GreetingUIIntents.NextButtonClicked -> openHome()
        }
    }

    private suspend fun getCandidate() {
        getCandidateUseCase().collect { resource ->
            when(resource) {
                is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                is Resource.Success -> _state.update {
                    Thread.sleep(3_000)
                    it.copy(data = resource.data, isLoading = false)
                }
                is Resource.Error -> _state.update {
                    Thread.sleep(3_000)
                    it.copy(dataNotFound = true, isLoading = false)
                }
            }
        }
    }

    private fun showPhoto(uri: Uri) {
        _state.update { it.copy(openImageInScreen = uri) }
    }

    private fun openHome() {
        _state.update { it.copy(shouldOpenHomeScreen = true) }
    }
}