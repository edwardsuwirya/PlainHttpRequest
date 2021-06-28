package com.enigmacamp.myplainhttprequest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enigmacamp.myapplication.data.remote.responses.ImageResponse
import com.enigmacamp.myplainhttprequest.data.repository.PlainRepository
import kotlin.concurrent.thread

class MainActivityViewModel(private val plainRepository: PlainRepository) : ViewModel() {

    private var _imageResponseLiveData = MutableLiveData<ImageResponse>()
    val imageResponseLiveData: LiveData<ImageResponse>
        get() {
            return _imageResponseLiveData
        }

    fun getImageList(searchName: String) {
        thread(start = true) {
            val result = plainRepository.getImage(imageName = searchName)
            _imageResponseLiveData.postValue(result)
        }

    }
}