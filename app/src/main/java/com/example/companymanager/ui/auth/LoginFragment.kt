package com.example.companymanager.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.companymanager.databinding.FragmentLoginBinding
import com.example.companymanager.data.network.AuthApi
import com.example.companymanager.data.network.Resource
import com.example.companymanager.data.repository.AuthRepository
import com.example.companymanager.home.HomeActivity
import com.example.companymanager.ui.base.BaseFragment
import com.example.companymanager.ui.enable
import com.example.companymanager.ui.handleApiError
import com.example.companymanager.ui.startNewActivity
import com.example.companymanager.ui.visible
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.progressbar.visible(false)
        binding.buttonLogin.enable(false)
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {

            binding.progressbar.visible(it is Resource.Loading)

            when(it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveAuthToken(it.value.token)
                        requireActivity().startNewActivity(HomeActivity::class.java)
                    }
                }
                is Resource.Failure -> handleApiError(it){login()}
            }
        })

        binding.editTextTextPassword.addTextChangedListener {
            val username = binding.editTextTextEmailAddress.text.toString().trim()
            binding.buttonLogin.enable(username.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.buttonLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val email = binding.editTextTextEmailAddress.text.toString().trim()
        val password = binding.editTextTextPassword.text.toString().trim()
        viewModel.login(email, password)
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)
}