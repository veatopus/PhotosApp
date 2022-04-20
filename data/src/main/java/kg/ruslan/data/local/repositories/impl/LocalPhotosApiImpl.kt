package kg.ruslan.data.local.repositories.impl

import android.content.Context
import androidx.lifecycle.LiveData
import kg.ruslan.core.apis.models.Photo
import kg.ruslan.core.apis.repositories.LocalPhotosApi
import kg.ruslan.core.base.BaseRepository
import kg.ruslan.core.resource.Resource

class LocalPhotosApiImpl(
    private val context: Context
): BaseRepository(), LocalPhotosApi {

    override fun getLocalPhotos(): LiveData<Resource<Photo>> = liveData {
        
    }
}