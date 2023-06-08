package com.example.chattingapp.api

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/userlist.php")
    fun getData(): Call<JsonElement>

    //We can add more api end points here
}