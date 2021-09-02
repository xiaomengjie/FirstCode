package com.example.firstcode.chapter15.logic.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {

    private const val BASE_URL = "https://api.caiyunapp.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor {
                Log.e("OKHttp", it)
            }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(clazz: Class<T>): T = retrofit.create(clazz)

    inline fun <reified T> create(): T = create(T::class.java)
}