package kg.ruslan.feature_photos.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.ruslan.core.resource.Resource
import kg.ruslan.domain.use_cases.GetLocalPhotosUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PhotosViewModel(
    val getLocalPhotosUseCase: GetLocalPhotosUseCase
): ViewModel() {

    private var _state: MutableStateFlow<PhotosState> =
        MutableStateFlow(PhotosState())
    var state: StateFlow<PhotosState> = _state

    val handleSideEffects: (PhotosUIIntents) -> Unit

    init {
        handleSideEffects = { intent ->
            viewModelScope.launch(Dispatchers.IO) {
                handleIntent(intent = intent)
            }
        }
    }

    private suspend fun handleIntent(intent: PhotosUIIntents) {
        when(intent) {
            is PhotosUIIntents.GetPhotos -> getPhotos()
            is PhotosUIIntents.PhotoClicked -> openGallery()
        }
    }

    private suspend fun getPhotos() {
        getLocalPhotosUseCase().collect { resource ->
            when(resource) {
                is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                is Resource.Error -> _state.update { it.copy(isLoading = false, errorMessage = resource.message) }
                is Resource.Success -> _state.update {
                    if (resource.data.isNotEmpty()) it.copy(isLoading = false, data = resource.data)
                    else it.copy(isLoading = false)
                }
            }
        }
    }

    private fun openGallery() {}
}