package com.example.profile.service

import com.example.profile.models.Profile
import com.example.profile.models.Users
import retrofit2.Response
import retrofit2.http.GET

interface ProfileApi {
    @GET("users")
    suspend fun getAllUsers(): Response<Users>

    @GET("config")
    suspend fun getProfileList(): Response<Profile>
}