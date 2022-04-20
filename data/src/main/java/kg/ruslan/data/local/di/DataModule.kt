package kg.ruslan.data.local.di

import kg.ruslan.core.apis.repositories.CandidateInfoApi
import kg.ruslan.core.apis.repositories.LocalPhotosApi
import kg.ruslan.data.local.repositories.impl.CandidateInfoApiImpl
import kg.ruslan.data.local.repositories.impl.LocalPhotosApiImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    factory<CandidateInfoApi> { CandidateInfoApiImpl(utils = get()) }
    factory<LocalPhotosApi> { LocalPhotosApiImpl(context = androidContext()) }
}