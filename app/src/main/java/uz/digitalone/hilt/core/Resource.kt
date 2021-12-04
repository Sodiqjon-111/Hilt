package uz.digitalone.hilt.core

sealed class Resource<out T>(val status: Status) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    object Loading : Resource<Nothing>(Status.LOADING)
    data class Success<out T>(val data: T) : Resource<T>(Status.SUCCESS)
    data class Failure(val exception: Exception, val    errorCode: Int = 400) :
        Resource<Nothing>(Status.ERROR)
}