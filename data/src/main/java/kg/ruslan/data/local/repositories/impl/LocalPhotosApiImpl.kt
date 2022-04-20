package kg.ruslan.data.local.repositories.impl

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.LiveData
import kg.ruslan.core.apis.models.Photo
import kg.ruslan.core.apis.repositories.LocalPhotosApi
import kg.ruslan.core.base.BaseRepository
import kg.ruslan.core.resource.Resource
import kg.ruslan.data.errors.StoragePermissionsNotReceived

class LocalPhotosApiImpl(
    private val context: Context
): BaseRepository(), LocalPhotosApi {

    @SuppressLint("Recycle")
    override fun getLocalPhotos(): LiveData<Resource<List<Photo>>> = liveData {
        asyncEmit(Resource.Loading())
        try {
            val data = mutableListOf<Photo>()
            context.contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null
            )?.use {  cursor ->

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
                asyncEmit(Resource.Success(data = data))
            }
        } catch (e: Exception) {
            val error = StoragePermissionsNotReceived()
            asyncEmit(Resource.Error(error = error, message = error.getMessageTask()))
        }

    }
}