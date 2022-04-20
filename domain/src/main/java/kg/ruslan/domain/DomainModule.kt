package kg.ruslan.domain

import kg.ruslan.domain.use_cases.GetCandidateUseCase
import kg.ruslan.domain.use_cases.GetLocalPhotosUseCase
import org.koin.dsl.module

val domainModule = module {
    // from local photos api
    factory { GetCandidateUseCase(get()) }

    // from photos api
    factory { GetLocalPhotosUseCase(get()) }
}