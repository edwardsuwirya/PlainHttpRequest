package com.enigmacamp.myplainhttprequest.data.remote

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class HttpRequestHandler {

    fun requestGet(requestUrl: String): String {
        val url = URL(requestUrl)
        val conn = url.openConnection() as HttpURLConnection
        conn.requestMethod = "GET"
        val responseCode = conn.responseCode
        return if (responseCode == HttpURLConnection.HTTP_OK) {
            val inputStream = BufferedReader(InputStreamReader(conn.inputStream))
            val response = StringBuffer()
            var inputLine: String?
            while (inputStream.readLine().also { inputLine = it } != null) {
                response.append(inputLine)
            }
            inputStream.close()
            response.toString()
        } else {
            ""
        }
    }
}