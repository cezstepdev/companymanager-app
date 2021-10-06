package com.example.companymanager.data.network

import com.example.companymanager.data.responses.LoginResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {

    @Headers("Content-Type: application/json")
    @POST("/login")
    suspend fun login(
        @Body params: RequestBody
    ) : LoginResponse
}