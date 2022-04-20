package kg.ruslan.core.apis.repositories

import androidx.lifecycle.LiveData
import kg.ruslan.core.apis.models.Photo
import kg.ruslan.core.resource.Resource

interface LocalPhotosApi {
    fun getLocalPhotos(): LiveData<Resource<Photo>>
}