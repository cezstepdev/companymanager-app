package com.example.companymanager.data.network

import com.example.companymanager.data.responses.UserResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

    @GET("/api/v1/user")
    suspend fun getUser(): UserResponse

    @POST("/logout")
    suspend fun logout(): ResponseBody
}