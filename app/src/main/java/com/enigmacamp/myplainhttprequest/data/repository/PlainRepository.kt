package com.enigmacamp.myplainhttprequest.data.repository

import com.enigmacamp.myapplication.data.remote.responses.ImageResponse
import com.enigmacamp.myapplication.data.remote.responses.ImageResult
import com.enigmacamp.myplainhttprequest.data.remote.PixabayApi
import org.json.JSONObject

class PlainRepository(private val pixabayApi: PixabayApi) {
    fun getImage(imageName: String): ImageResponse {
        val result = pixabayApi.searchForImage(imageName)
        val jsonResult = JSONObject(result)
        val hitsResponse = jsonResult.getJSONArray("hits")
        val hits = mutableListOf<ImageResult>()
        for (i in 0 until hitsResponse.length()) {
            val hit = hitsResponse.optJSONObject(i)
            val imageResult = ImageResult(
                hit.optInt("comments"),
                hit.optInt("downloads"),
                hit.optInt("favorites"),
                hit.optInt("id"),
                hit.optInt("imageHeight"),
                hit.optInt("imageSize"),
                hit.optInt("imageWidth"),
                hit.optString("largeImageURL"),
                hit.optInt("likes"),
                hit.optString("pageURL"),
                hit.optInt("previewHeight"),
                hit.optString("previewURL"),
                hit.optInt("previewWidth"),
                hit.optString("tags"),
                hit.optString("type"),
                hit.optString("user"),
                hit.optInt("id"),
                hit.optString("userImageURL"),
                hit.optInt("views"),
                hit.optInt("webformatHeight"),
                hit.optString("webformatURL"),
                hit.optInt("webformatWidth")
            )
            hits.add(imageResult)
        }

        return ImageResponse(
            total = jsonResult.optInt("total"),
            totalHits = jsonResult.optInt("totalHits"),
            hits = hits
        )
    }
}