package kg.ruslan.data.local.repositories.impl

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.LiveData
import kg.ruslan.core.models.Photo
import kg.ruslan.core.apis.repositories.LocalPhotosApi
import kg.ruslan.core.base.BaseRepository
import kg.ruslan.core.resource.Resource
import kg.ruslan.data.errors.StoragePermissionsNotReceived
import kotlinx.coroutines.flow.Flow

class LocalPhotosApiImpl(
    private val context: Context
) : BaseRepository(), LocalPhotosApi {

    @SuppressLint("Recycle")
    override fun getLocalPhotos(): Flow<Resource<List<Photo>>> = doRequest {
        val data = mutableListOf<Photo>()
        context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            null
        )?.use { cursor ->
            // Cache column indices.
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)

                val contentUri: Uri = ContentUris.withAppendedId(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    id
                )
                data.add(Photo(uri = contentUri))
            }
            emit(Resource.Success(data = data))
        }


    }
}