package uz.digitalone.hilt.repo_imp

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import uz.digitalone.hilt.core.Resource
import uz.digitalone.hilt.remote.AuthDataSource
import uz.digitalone.hilt.repo.AuthRepo
import uz.digitalone.hilt.response.UserModel
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ActivityRetainedScoped
class AuthRepoImp @Inject constructor(private val authDataSource: AuthDataSource) : AuthRepo {
    override fun getAllUser(): LiveData<Resource<List<UserModel>>> = liveData {
        emit(
            authDataSource.getAlluser()
        )
    }


}