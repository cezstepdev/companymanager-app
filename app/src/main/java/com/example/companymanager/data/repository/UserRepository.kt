package com.example.companymanager.data.repository

import com.example.companymanager.data.network.UserApi

class UserRepository(
    private val api: UserApi,
) : BaseRepository() {

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }
}