package kg.ruslan.data.local.di

import kg.ruslan.core.apis.repositories.CandidateInfoApi
import kg.ruslan.data.local.repositories.impl.CandidateInfoApiImpl
import org.koin.dsl.module

val dataModule = module {
    factory<CandidateInfoApi> { CandidateInfoApiImpl(utils = get()) }
}