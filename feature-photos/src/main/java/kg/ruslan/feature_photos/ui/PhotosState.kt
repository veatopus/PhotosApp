package kg.ruslan.feature_photos.ui

import android.net.Uri
import kg.ruslan.core.models.Photo

data class PhotosState(
    val isLoading: Boolean = false,
    val data: List<Photo> = listOf(),
    val errorMessage: String? = null,
    val openImageInScreen: Uri? = null
)

sealed class PhotosUIIntents {
    data class PhotoClicked(val uriOfImage: Uri): PhotosUIIntents()
    object GetPhotos : PhotosUIIntents()
}