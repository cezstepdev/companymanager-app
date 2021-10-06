package com.example.companymanager.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.companymanager.data.responses.UserResponseItem
import com.example.companymanager.databinding.UserItemLayoutBinding

class UsersAdapter(private val users: List<UserResponseItem>): RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    class UserViewHolder(private val binding: UserItemLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(user: UserResponseItem){
            binding.user = user
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(UserItemLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    override fun getItemCount() = users.size
}