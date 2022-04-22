package kg.ruslan.domain.use_cases

import kg.ruslan.core.apis.repositories.LocalPhotosApi

class GetLocalPhotosUseCase(
    private val api: LocalPhotosApi
) {
    operator fun invoke() = api.getLocalPhotos()
}