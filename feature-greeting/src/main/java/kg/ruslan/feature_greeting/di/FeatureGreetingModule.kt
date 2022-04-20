package kg.ruslan.feature_greeting.di

import kg.ruslan.feature_greeting.ui.GreetingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureGreetingModule = module {
    viewModel { GreetingViewModel(get()) }
}