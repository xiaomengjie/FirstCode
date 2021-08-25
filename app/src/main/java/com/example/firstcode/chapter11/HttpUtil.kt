package com.example.firstcode.chapter11

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

object HttpUtil {

    fun sendRequestWithHttpURLConnection(url: String, callbackListener: HttpCallbackListener){
        thread {
            var connection: HttpURLConnection? = null
            try {
                connection = URL(url).openConnection() as HttpURLConnection
                connection.connectTimeout = 3000
                connection.readTimeout = 3000
                val response = StringBuilder()
                val bufferedReader = BufferedReader(InputStreamReader(connection.inputStream))
                bufferedReader.use {
                    it.forEachLine { line ->
                        response.append(line)
                    }
                }
                callbackListener.onFinish(response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
                callbackListener.onError(e)
            } finally {
                connection?.disconnect()
            }
        }
    }

    fun sendRequestWithOkHttp(url: String, callback: okhttp3.Callback){
        val okHttpClient = OkHttpClient()
        val request = Request.Builder().url(url).build()
        okHttpClient.newCall(request).enqueue(callback)
    }
}

interface HttpCallbackListener{
    fun onFinish(response: String)

    fun onError(exception: Exception)
}