package com.example.profile.activities.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profile.models.Profile
import com.example.profile.models.UserDetails
import com.example.profile.models.Users
import com.example.profile.service.RetrofitHelper
import com.example.profile.utils.Const
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val api: RetrofitHelper) : ViewModel() {

    private lateinit var users: List<UserDetails>
    val usersMutableLiveData = MutableLiveData<Pair<Profile, Users>>()
    val errorMutableLiveData = MutableLiveData<String>()
    private var currentUser = 0

    fun getNextUser(): UserDetails? {
        return if (users.isEmpty()) null
        else users[currentUser++ % users.size]
    }

    private fun getUsers(profileOrder: Profile) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val profileResponse = api.getRetrofit().getAllUsers()
                if (profileResponse.isSuccessful) {
                    profileResponse.body()?.let {
                        users = it.users
                        usersMutableLiveData.postValue(Pair(profileOrder, it))
                        currentUser++
                    }
                } else {
                    errorMutableLiveData.postValue(Const.NETWORK_ERROR_OCCURRED)
                }

            } catch (e: Exception) {
                errorMutableLiveData.postValue(e.message)
            }
        }
    }

    fun getUserList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val profileResponse = api.getRetrofit().getProfileList()
                if (profileResponse.isSuccessful) {
                    profileResponse.body()?.let {
                        getUsers(it)
                    }
                } else {
                    errorMutableLiveData.postValue(Const.NETWORK_ERROR_OCCURRED)
                }

            } catch (e: Exception) {
                errorMutableLiveData.postValue(e.message)
            }
        }
    }
}