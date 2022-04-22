package kg.ruslan.data.local.repositories.impl

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import kg.ruslan.core.apis.repositories.LocalPhotosApi
import kg.ruslan.core.base.BaseRepository
import kg.ruslan.core.models.Photo
import kg.ruslan.core.resource.Resource
import kotlinx.coroutines.flow.Flow

class LocalPhotosApiImpl(
    private val context: Context
) : BaseRepository(), LocalPhotosApi {

    override fun getLocalPhotos(): Flow<Resource<List<Photo>>> = doRequest {
        emit(Resource.Loading())
        var counts = 0
        val data = mutableListOf<Photo>()
        val projection = arrayOf(
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.DATE_TAKEN
        )
        val images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val orderBy = MediaStore.Images.Media.DATE_TAKEN
        val cur = context.contentResolver.query(
            images, projection,
            // Which
            // columns
            // to return
            null,  // Which rows to return (all rows)
            null,  // Selection arguments (none)
            "$orderBy DESC" // Ordering
        )
        if (cur?.moveToNext() == true) {
            val bucketColumn: Int = cur.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
            val dateColumn: Int = cur.getColumnIndex(MediaStore.Images.Media.DATE_TAKEN)
            do {
                val columnIndex = cur.getColumnIndex(MediaStore.Images.Media.DATA)

                if (columnIndex >= 0) {
                    data.add(0, Photo(Uri.parse(
                        cur.getString(columnIndex)
                    )))
                    counts++
                } else {
                    emit(Resource.Error(message = "files not found"))
                }

            } while (cur.moveToNext() && counts <= 100)
        }
        emit(Resource.Success(data = data))
    }
}