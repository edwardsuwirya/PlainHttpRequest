package com.enigmacamp.myplainhttprequest.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enigmacamp.myapplication.data.remote.responses.ImageResponse
import com.enigmacamp.myplainhttprequest.data.repository.PlainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivityViewModel(private val plainRepository: PlainRepository) : ViewModel() {
    private val scope = CoroutineScope(context = Dispatchers.IO)
    private var _imageResponseLiveData = MutableLiveData<ImageResponse>()
    val imageResponseLiveData: LiveData<ImageResponse>
        get() {
            return _imageResponseLiveData
        }

    fun getImageList(searchName: String) {
//        thread(start = true) {
//            val result = plainRepository.getImage(imageName = searchName)
//            _imageResponseLiveData.postValue(result)
//        }
//
//        Menggunakan lifecycle viewmodel KTX
//        viewModelScope.launch(context = Dispatchers.IO) {
//            val result = plainRepository.getImage(imageName = searchName)
//            _imageResponseLiveData.postValue(result)
//        }
        scope.launch {
            val result = plainRepository.getImage(imageName = searchName)
            _imageResponseLiveData.postValue(result)
        }

    }

//    Tidak perlu method onCleared kalo menggunakn viewmodel KTX
    override fun onCleared() {
        Log.d("MainActivityViewModel", "onCleared: ")
        scope.cancel()
    }
}