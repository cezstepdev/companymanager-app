package com.example.companymanager.data.repository

import com.example.companymanager.data.UserPreferences
import com.example.companymanager.data.network.AuthApi
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject

class AuthRepository(
    private val api: AuthApi,
    private val preferences: UserPreferences
) : BaseRepository() {

    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(createJsonRequestBody(
            "username" to email, "password" to password))
    }

    private fun createJsonRequestBody(vararg params: Pair<String, String>) =
        RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            JSONObject(mapOf(*params)).toString())

    suspend fun saveAuthToken(token: String) {
        preferences.saveAuthToken(token)
    }
}