package kg.ruslan.feature_photos.ui

import android.Manifest
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kg.ruslan.core.base.BaseFragment
import kg.ruslan.core.models.Photo
import kg.ruslan.core.ui.dismissProgress
import kg.ruslan.core.ui.showErrorAlert
import kg.ruslan.core.ui.showProgress
import kg.ruslan.feature_photos.databinding.FragmentPhotosBinding
import kg.ruslan.feature_photos.ui.recyclerview.PhotosAdapter
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotosFragment : BaseFragment<FragmentPhotosBinding>(FragmentPhotosBinding::inflate) {

    private val viewModel: PhotosViewModel by viewModel()
    private val adapter: PhotosAdapter by lazy {
        PhotosAdapter()
    }

    override fun initialize() {
        super.initialize()
        askReadFilesPermission()
        bindState(state = viewModel.state)
        onBind()
    }

    private fun askReadFilesPermission() {
        requestPermission(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            if (it) {
                viewModel.handleSideEffects(PhotosUIIntents.GetPhotos)
            } else showErrorAlert(rMessage = "This App Cannot Work Without Permissions") {
                askReadFilesPermission()
            }
        }
    }

    private fun bindState(state: StateFlow<PhotosState>) {
        state.partialListener(::initData) { it.data }
        state.partialListener(::progress) { it.isLoading }
        state.partialListener(::showErrorMessage) { it.errorMessage }
    }

    private fun onBind() {
        binding.recyclerView.run {
           adapter = this@PhotosFragment.adapter
            recycledViewPool.setMaxRecycledViews(
                1, 20
            )
        }
    }

    private fun initData(data: List<Photo>) {
        adapter.submitList(data)
    }

    private fun progress(isProgress: Boolean) {
        if (isProgress) showProgress("loading data...")
        else dismissProgress()
    }

    private fun showErrorMessage(message: String?) {
        if (message != null) {
            showErrorMessage(message)
        }
    }

}