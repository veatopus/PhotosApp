package kg.ruslan.data.local.repositories.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kg.ruslan.core.apis.models.Candidate
import kg.ruslan.core.apis.repositories.CandidateInfoApi
import kg.ruslan.core.halpers.Utils
import kg.ruslan.core.resource.Resource

class CandidateInfoApiImpl(
    private val utils: Utils
): CandidateInfoApi {
    override fun getCandidate(): LiveData<Resource<Candidate>> {
        val liveData = MutableLiveData<Resource<Candidate>>()
        liveData.postValue(Resource.Loading())

        val jsonData = utils.getJsonFromAssets("candidate")
        if(jsonData.isNullOrBlank()) {
            val data: Candidate = Gson()
                .fromJson(
                    jsonData,
                    object : TypeToken<Candidate>() {}.type
                )
            liveData.postValue(Resource.Success(data = data))
        } else {
            liveData.postValue(Resource.Error(message = "data not found"))
        }

        return liveData
    }
}