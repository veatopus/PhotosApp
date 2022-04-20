package kg.ruslan.domain.use_cases

import kg.ruslan.core.apis.repositories.CandidateInfoApi

class GetCandidateUseCase(
    private val api: CandidateInfoApi
) {
    operator fun invoke() = api.getCandidate()
}