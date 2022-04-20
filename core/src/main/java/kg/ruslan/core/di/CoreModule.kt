package kg.ruslan.core.di

import kg.ruslan.core.halpers.Utils
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val coreModule = module {
    factory { Utils(androidApplication()) }
}
