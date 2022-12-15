package com.imaginato.homeworkmvvm.data.remote.demo

import com.imaginato.homeworkmvvm.data.remote.demo.response.DemoResponse
import com.imaginato.homeworkmvvm.data.remote.login.response.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface DemoApi {

    @GET
    fun getDemoDataAsync(@Url url: String): Deferred<DemoResponse>

    @POST
    fun loginUserAsync(@Url url: String, @Body body: HashMap<String, Any>): Deferred<retrofit2.Response<LoginResponse>>
}