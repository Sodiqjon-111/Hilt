package uz.digitalone.hilt.service

import retrofit2.http.GET
import uz.digitalone.hilt.response.UserModel


interface Auth {
    @GET("posts")
    suspend fun getAllUser(): List<UserModel>


}