package com.example.profile.service

import com.example.profile.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASEURL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRetrofit(): ProfileApi {
        return retrofit.create(ProfileApi::class.java)
    }

}