package com.enigmacamp.myplainhttprequest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.enigmacamp.myplainhttprequest.R
import com.enigmacamp.myplainhttprequest.data.remote.HttpRequestHandler
import com.enigmacamp.myplainhttprequest.data.remote.PixabayApi
import com.enigmacamp.myplainhttprequest.data.repository.PlainRepository

/*
    HttpURLConnection, android and java standard networking class
 */
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        subscribe()
        viewModel.getImageList("flower")
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val pixabayApi = PixabayApi(HttpRequestHandler())
                val plainRepository = PlainRepository(pixabayApi)
                return MainActivityViewModel(plainRepository) as T
            }
        }).get(MainActivityViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.imageResponseLiveData.observe(this) {
            Log.d("MainActivity", "subscribe: $it")
        }
    }
}