package uz.digitalone.hilt.repo

import androidx.lifecycle.LiveData
import uz.digitalone.hilt.core.Resource
import uz.digitalone.hilt.response.UserModel

interface AuthRepo {
       fun getAllUser(): LiveData<Resource<List<UserModel>>>
}