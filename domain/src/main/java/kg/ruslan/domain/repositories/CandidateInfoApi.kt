package kg.ruslan.domain.repositories

import kg.ruslan.core.resource.Resource
import kg.ruslan.domain.models.Candidate
import kotlinx.coroutines.flow.Flow

interface CandidateInfoApi {
    fun getCandidate(): Flow<Resource<Candidate>>
}