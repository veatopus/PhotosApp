package kg.ruslan.data.errors

class StoragePermissionsNotReceived: AppException() {
    override fun getMessageTask(): String = "storage permission not received"
}