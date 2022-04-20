package kg.ruslan.data.local.repositories.impl

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kg.ruslan.core.apis.repositories.CandidateInfoApi
import kg.ruslan.core.base.BaseRepository
import kg.ruslan.core.halpers.Utils
import kg.ruslan.core.models.Candidate
import kg.ruslan.core.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CandidateInfoApiImpl(
    private val utils: Utils
): BaseRepository(), CandidateInfoApi {

    override fun getCandidate(): Flow<Resource<Candidate>> = flow {
        emit(Resource.Loading())
        val jsonData = utils.getJsonFromAssets("candidate")
        if(jsonData.isNullOrBlank()) {
            val data: Candidate = Gson()
                .fromJson(
                    jsonData,
                    object : TypeToken<Candidate>() {}.type
                )
            emit(Resource.Success(data = data))
        } else {
            emit(Resource.Error(message = "data not found"))
        }
    }

}