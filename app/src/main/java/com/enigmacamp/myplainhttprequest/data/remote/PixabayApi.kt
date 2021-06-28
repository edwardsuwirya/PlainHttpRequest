package com.enigmacamp.myplainhttprequest.data.remote

import com.enigmacamp.myplainhttprequest.BuildConfig

class PixabayApi(private val httpRequestHandler: HttpRequestHandler) {

    fun searchForImage(searchQuery: String) =
        httpRequestHandler.requestGet("${BuildConfig.BASE_URL}/?key=${BuildConfig.API_KEY}&q=$searchQuery")
}