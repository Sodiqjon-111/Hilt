package uz.digitalone.hilt.data

import com.google.gson.GsonBuilder
import logd
import retrofit2.Response
import uz.digitalone.hilt.core.Resource
import uz.digitalone.hilt.model.ErrorResponseModel

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.Success(body)
            }
            logd(response.errorBody().toString())
            val reader = response.errorBody()?.charStream()
            var messageText: String? = null
            if (reader != null) {
                val gson = GsonBuilder().create()
                val message =
                    gson.fromJson(
                        reader,
                        ErrorResponseModel::class.java
                    )
                messageText = message.message
                logd(message.message ?: "unknown")
            }
            return error(Exception("${response.code()} $messageText"), errorCode = response.code())
        } catch (e: Exception) {
            return error(e)
        }
    }

    private fun <T> error(exception: Exception, errorCode: Int =400): Resource<T> {
        logd(exception.message ?: exception.toString())
        return Resource.Failure(exception = exception, errorCode = errorCode)
    }

}