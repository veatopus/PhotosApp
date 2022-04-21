package kg.ruslan.core.apis.repositories

import kg.ruslan.core.models.Candidate
import kg.ruslan.core.models.CandidateDto
import kg.ruslan.core.resource.Resource
import kotlinx.coroutines.flow.Flow

interface CandidateInfoApi {
    fun getCandidate(): Flow<Resource<CandidateDto>>
}