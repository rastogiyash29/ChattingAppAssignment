package com.example.chattingapp.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chattingapp.models.ChatModel
import com.example.chattingapp.repository.Repository
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: Repository):ViewModel() {

    var ChatModelsLiveData=MutableLiveData<ArrayList<ChatModel>>()

    fun fetchData(){
        repository.getData().enqueue(
            object : Callback<JsonElement> {
                override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                    if (response.isSuccessful) {
                        val jsonElement: JsonElement? = response.body()
                        // Handle the variable JSON response
                        jsonElement?.let {
                            when {
                                it.isJsonObject -> {
                                    //Handle JSON Object
                                    //Since We will not be getting Json Object so we are Ignoring here
                                }
                                it.isJsonArray -> {
                                    // Handle JSON array
                                   val chatModelArrayList=ArrayList<ChatModel>()
                                    for(jsonObject in it.asJsonArray){
                                        val currJsonObj=jsonObject.asJsonObject
                                        val currChatModel=ChatModel(
                                            currJsonObj.get("username").asString,
                                            currJsonObj.get("email").asString,
                                            currJsonObj.get("profile").asString
                                        )
                                        chatModelArrayList.add(currChatModel)
                                    }
                                    ChatModelsLiveData.value=chatModelArrayList
                                }
                                else -> {
                                    // Handle other types of JSON elements (e.g., primitive values)
                                    //Since We will not be getting Json Primitive DataType so we are Ignoring here
                                }
                            }
                        }
                    } else {
                        // Handle error response
                        Log.d("tag","Some Request Error Occurred ${response.toString()}")
                    }
                }

                override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                    Log.d("tag","Some Internal Error Occurred ${t}")
                }
            }
        )
    }

}