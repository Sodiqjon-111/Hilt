package uz.digitalone.hilt.response

import com.google.gson.annotations.SerializedName

data class UserModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String

)
