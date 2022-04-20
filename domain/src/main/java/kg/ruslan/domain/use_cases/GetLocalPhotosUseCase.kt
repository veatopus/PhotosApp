package kg.ruslan.domain.use_cases

import kg.ruslan.domain.repositories.LocalPhotosApi

class GetLocalPhotosUseCase(
    private val api: LocalPhotosApi
) {
    operator fun invoke() = api.getLocalPhotos()
}