package com.example.companymanager.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.companymanager.data.network.Resource
import com.example.companymanager.data.network.UserApi
import com.example.companymanager.data.repository.UserRepository
import com.example.companymanager.data.responses.UserResponse
import com.example.companymanager.databinding.FragmentUsersBinding
import com.example.companymanager.ui.base.BaseFragment
import com.example.companymanager.ui.handleApiError
import com.example.companymanager.ui.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class UsersFragment : BaseFragment<UsersViewModel, FragmentUsersBinding, UserRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.progressbar.visible(false)
        viewModel.getUser()
        viewModel.user.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visible(it is Resource.Loading)

            when(it) {
                is Resource.Success -> {
                    binding.progressbar.visible(false)
                    binding.usersListRecyclerView.adapter = UsersAdapter(it.value)
                }
            }
        })
    }

    override fun getViewModel() = UsersViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentUsersBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking {userPreferences.authToken.first()}
        val api = remoteDataSource.buildApi(UserApi::class.java, token)
        return UserRepository(api)
    }
}