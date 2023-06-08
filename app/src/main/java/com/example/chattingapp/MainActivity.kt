package com.example.chattingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chattingapp.adapters.RecyclerViewAdapter
import com.example.chattingapp.databinding.ActivityMainBinding
import com.example.chattingapp.models.ChatModel
import com.example.chattingapp.repository.Repository
import com.example.chattingapp.viewmodels.MainViewModel
import com.example.chattingapp.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel:MainViewModel
    private var chatModelsList=ArrayList<ChatModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        //Initialising repository
        val repositor=Repository()

        //Initialising ViewModelFactory
        val viewModelFactory=MainViewModelFactory(repositor)

        //Initialising MainViewModel
        viewModel=ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

        //setting up LiveData observers of ViewModel
        viewModel.ChatModelsLiveData.observe(this) {
            chatModelsList = it
            binding.progressBar.visibility = View.INVISIBLE
            renderData()
        }

        //Now fetching Data from internet
        viewModel.fetchData()
    }

    private fun renderData() {
        val recyclerViewAdapter=RecyclerViewAdapter(this,chatModelsList)
        binding.recyclerView.adapter=recyclerViewAdapter
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
    }

}