package com.imaginato.homeworkmvvm.data.remote.login.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("errorCode")
    val errorCode: String,
    @SerializedName("errorMessage")
    val errorMessage: String
) {
    data class Data(
        @SerializedName("isDeleted")
        val isDeleted: Boolean,
        @SerializedName("userId")
        val userId: String,
        @SerializedName("userName")
        val userName: String
    )
}