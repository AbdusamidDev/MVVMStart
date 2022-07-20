package develop.abdusamid.mvvm.utils

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, nothing: Nothing?): Resource<T> {
            return Resource(Status.ERROR, null, msg)
        }

        fun <T> loading(nothing: Nothing?): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }
    }
}