package kg.ruslan.core.apis.repositories

import androidx.lifecycle.LiveData
import kg.ruslan.core.apis.models.Candidate
import kg.ruslan.core.resource.Resource

interface CandidateInfoApi {
    fun getCandidate(): LiveData<Resource<Candidate>>
}