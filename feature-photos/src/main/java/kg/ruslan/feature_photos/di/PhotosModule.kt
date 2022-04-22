package kg.ruslan.feature_photos.di

import kg.ruslan.feature_photos.ui.PhotosViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val photosModule = module {
    viewModel { PhotosViewModel(get()) }
}