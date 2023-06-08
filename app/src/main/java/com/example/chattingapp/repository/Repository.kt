package com.example.chattingapp.repository

import com.example.chattingapp.api.RetrofitInstance
import com.google.gson.JsonElement
import retrofit2.Call

class Repository {

    fun getData(): Call<JsonElement>{
        return RetrofitInstance.apiService.getData()
    }

    //We can add more functions here
}