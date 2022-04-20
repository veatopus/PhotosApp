package kg.ruslan.data.local.repositories.impl

import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kg.ruslan.core.apis.models.Candidate
import kg.ruslan.core.apis.repositories.CandidateInfoApi
import kg.ruslan.core.base.BaseRepository
import kg.ruslan.core.halpers.Utils
import kg.ruslan.core.resource.Resource

class CandidateInfoApiImpl(
    private val utils: Utils
): BaseRepository(), CandidateInfoApi {

    override fun getCandidate(): LiveData<Resource<Candidate>> = liveData {
        val jsonData = utils.getJsonFromAssets("candidate")
        if(jsonData.isNullOrBlank()) {
            val data: Candidate = Gson()
                .fromJson(
                    jsonData,
                    object : TypeToken<Candidate>() {}.type
                )
            asyncEmit(Resource.Success(data = data))
        } else {
            asyncEmit(Resource.Error(message = "data not found"))
        }
    }

}