package kg.ruslan.data.local.repositories.impl

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kg.ruslan.core.apis.repositories.CandidateInfoApi
import kg.ruslan.core.base.BaseRepository
import kg.ruslan.core.halpers.Utils
import kg.ruslan.core.models.Candidate
import kg.ruslan.core.models.CandidateDto
import kg.ruslan.core.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException

class CandidateInfoApiImpl(
    private val utils: Utils
): BaseRepository(), CandidateInfoApi {

    override fun getCandidate(): Flow<Resource<CandidateDto>> = doRequest {
        val jsonData = utils.getJsonFromAssets("candidate.json")
        Log.e("ololo", "getCandidate: $jsonData")
        if(jsonData != null) {
            val data: CandidateDto = Gson()
                .fromJson(
                    jsonData,
                    object : TypeToken<CandidateDto>() {}.type
                )
            emit(Resource.Success(data = data))
        } else throw Error("json not found")
    }

}