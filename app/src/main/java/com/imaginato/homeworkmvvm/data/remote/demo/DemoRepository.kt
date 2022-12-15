package com.imaginato.homeworkmvvm.data.remote.demo

import com.imaginato.homeworkmvvm.data.remote.login.response.LoginResponse
import kotlinx.coroutines.flow.Flow

interface DemoRepository {

    suspend fun getDemoData(): Flow<String>
    suspend fun loginUser(username: String, password: String): Flow<retrofit2.Response<LoginResponse>>
}