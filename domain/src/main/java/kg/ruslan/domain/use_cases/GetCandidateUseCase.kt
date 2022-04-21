package kg.ruslan.domain.use_cases

import kg.ruslan.core.apis.repositories.CandidateInfoApi
import kg.ruslan.core.models.Candidate
import kg.ruslan.core.models.toCandidate
import kg.ruslan.core.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCandidateUseCase(
    private val api: CandidateInfoApi
) {

    operator fun invoke(): Flow<Resource<Candidate>> = api.getCandidate().map {
        when (it) {
            is Resource.Success -> Resource.Success(it.data.toCandidate())
            is Resource.Error -> Resource.Error(message = it.message, error = it.error)
            is Resource.Loading -> Resource.Loading()
        }

    }
}