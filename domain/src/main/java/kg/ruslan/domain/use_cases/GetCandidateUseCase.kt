package kg.ruslan.domain.use_cases

import kg.ruslan.domain.repositories.CandidateInfoApi

class GetCandidateUseCase(
    private val api: CandidateInfoApi
) {
    operator fun invoke() = api.getCandidate()
}