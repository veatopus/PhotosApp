package kg.ruslan.domain.repositories

import kg.ruslan.core.resource.Resource
import kg.ruslan.domain.models.Photo
import kotlinx.coroutines.flow.Flow

interface LocalPhotosApi {
    fun getLocalPhotos(): Flow<Resource<Photo>>
}