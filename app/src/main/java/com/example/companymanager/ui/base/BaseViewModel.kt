package com.example.companymanager.ui.base

import androidx.lifecycle.ViewModel
import com.example.companymanager.data.network.UserApi
import com.example.companymanager.data.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseViewModel (
    private val repository: BaseRepository
) : ViewModel() {

    suspend fun logout(api: UserApi) = withContext(Dispatchers.IO) {repository.logout(api)}
}

