package kg.ruslan.core.base

import kg.ruslan.core.resource.Resource
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

open class BaseRepository {
    protected fun<T> doRequest(block: suspend FlowCollector<Resource<T>>.() -> Unit) = flow {
        emit(Resource.Loading())
        try {
            block()
        } catch (e: Exception) {
            emit(Resource.Error(error = e, message = e.localizedMessage ?: "null"))
        }
    }
}


