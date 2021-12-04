package uz.digitalone.hilt.remote

import kotlinx.coroutines.ExperimentalCoroutinesApi
import logd
import uz.digitalone.hilt.core.Resource
import uz.digitalone.hilt.data.BaseDataSource
import uz.digitalone.hilt.response.UserModel
import uz.digitalone.hilt.service.Auth
import javax.inject.Inject

@ExperimentalCoroutinesApi
class AuthDataSource @Inject constructor(
    private val auth: Auth
) : BaseDataSource() {

    suspend fun getAlluser(): Resource<List<UserModel>> {
        logd("auth data source")
        return Resource.Success(
            auth.getAllUser()
        )

    }
}