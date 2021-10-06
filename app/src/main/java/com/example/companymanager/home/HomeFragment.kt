package com.example.companymanager.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.companymanager.R
import com.example.companymanager.data.network.UserApi
import com.example.companymanager.data.repository.UserRepository
import com.example.companymanager.databinding.FragmentHomeBinding
import com.example.companymanager.ui.base.BaseFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, UserRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonLogout.setOnClickListener {
            logout()
        }

        binding.expandUsers.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_usersFragment) }
    }

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking {userPreferences.authToken.first()}
        val api = remoteDataSource.buildApi(UserApi::class.java, token)
        return UserRepository(api)
    }
}