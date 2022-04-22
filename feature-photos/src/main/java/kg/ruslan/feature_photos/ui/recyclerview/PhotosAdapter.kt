package kg.ruslan.feature_photos.ui.recyclerview

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kg.ruslan.core.models.Photo
import kg.ruslan.core.ui.DefDiffUtil
import kg.ruslan.feature_photos.databinding.ItemPhotoBinding
import java.io.File


class PhotosAdapter: ListAdapter<Photo, PhotosAdapter.PhotoViewHolder>(DefDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(ItemPhotoBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class PhotoViewHolder(private val binding: ItemPhotoBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(photo: Photo) {
            val imgFile = File(photo.uri.toString())

            if (imgFile.exists()) {
                val myBitmap: Bitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
                binding.imageViewPhoto.setImageBitmap(Bitmap.createScaledBitmap(myBitmap, 400, 400, false))
            }
        }
    }
}