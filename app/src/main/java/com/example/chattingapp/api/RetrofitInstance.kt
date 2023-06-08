package com.example.chattingapp.api

import com.example.chattingapp.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitInstance {
    private val httpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(Constants.BASE_URL).client(httpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}