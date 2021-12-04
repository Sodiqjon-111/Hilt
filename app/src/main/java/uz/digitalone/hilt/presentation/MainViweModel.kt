package uz.digitalone.hilt.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.digitalone.hilt.core.Resource
import uz.digitalone.hilt.repo.AuthRepo
import uz.digitalone.hilt.response.UserModel
import javax.inject.Inject

@HiltViewModel
class MainViweModel @Inject constructor(
    private val repository: AuthRepo
) : ViewModel() {
    init {
        getUserList()
    }

    var userlist = MutableLiveData<Resource<List<UserModel>>>()

    fun getUserList() {
        repository.getAllUser().observeForever {
            userlist.value = it
        }
    }


}