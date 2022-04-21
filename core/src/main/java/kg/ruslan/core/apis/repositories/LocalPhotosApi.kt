package kg.ruslan.core.apis.repositories

import kg.ruslan.core.models.Photo
import kg.ruslan.core.resource.Resource
import kotlinx.coroutines.flow.Flow

interface LocalPhotosApi {
    fun getLocalPhotos(): Flow<Resource<List<Photo>>>
}