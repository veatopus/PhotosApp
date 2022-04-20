package kg.ruslan.core.resource

/**
 * [Resource] is using for transfer data between data and presentation layer
 *
 * @param data is some result from local or remote data source.
 * @param message is message from some error by trying get data from local/remote data source
 */
sealed class Resource<out T>(
    val data: T? = null,
    val message: String? = null,
) {
    /**
     * [Loading] intermediate value before get data or error
     */
    class Loading<T>(data: T? = null) : Resource<T>(data = data)

    /**
     * [Success] success response from data source
     */
    class Success<T>(data: T) : Resource<T>(data = data)

    /**
     * [Error] result got wrong. Error is state with message te notify receiver that something went wrong
     */
    class Error<T>(message: String, data: T? = null) : Resource<T>(data = data, message = message)
}