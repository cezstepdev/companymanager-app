package com.example.companymanager.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.companymanager.data.repository.AuthRepository
import com.example.companymanager.data.repository.BaseRepository
import com.example.companymanager.data.repository.UserRepository
import com.example.companymanager.home.HomeViewModel
import com.example.companymanager.home.UsersViewModel
import com.example.companymanager.ui.auth.AuthViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as UserRepository) as T
            modelClass.isAssignableFrom(UsersViewModel::class.java) -> UsersViewModel(repository as UserRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass not found")
        }
    }
}