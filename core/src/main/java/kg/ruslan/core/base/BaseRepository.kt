package kg.ruslan.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kg.ruslan.core.resource.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

open class BaseRepository {

    private val customCoroutineContext: CoroutineContext = Job() + Dispatchers.IO + CoroutineName(name = "async posting value to the liveData")

    /**
     * The function [liveData] is using to do some operation asynchronously and have ability to post some data into livedata immediately
     *
     * @param block using [BaseRepository.liveData] to do some request within [LiveData]
     * @return [LiveData] to observe function and recurve some data from it
     *
     * !!
     * Use custom [BaseRepository.asyncEmit] or [LiveData.postValue] to safety post value to the LiveData
     */
    @OptIn(DelicateCoroutinesApi::class)
    fun <T> liveData(block: MutableLiveData<T>.() -> Unit): LiveData<T> {
        val liveData = MutableLiveData<T>()
        GlobalScope.launch(customCoroutineContext) {
            liveData.block()
        }
        return liveData
    }

    /**
     * The function asyncEmit is using to post some data to the liveData asynchronously
     *
     * @param value is some data to post to the liveData
     */
    @OptIn(DelicateCoroutinesApi::class)
    protected fun<T> MutableLiveData<T>.asyncEmit(value: T) {
        GlobalScope.launch(customCoroutineContext) {
            postValue(value)
        }
    }

    protected fun<T> doRequest(block: suspend FlowCollector<Resource<T>>.() -> Unit) = flow {
        emit(Resource.Loading())
        try {
            block()
        } catch (e: Exception) {
            emit(Resource.Error(error = e, message = e.localizedMessage ?: "null"))
        }
    }
}


