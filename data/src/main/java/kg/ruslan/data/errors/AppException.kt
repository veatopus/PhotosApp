package kg.ruslan.data.errors

abstract class AppException(
    message: String? = null,
    exception: Throwable? = null
) : Throwable(message = message, cause = exception) {
    abstract fun getMessageTask() : String
}