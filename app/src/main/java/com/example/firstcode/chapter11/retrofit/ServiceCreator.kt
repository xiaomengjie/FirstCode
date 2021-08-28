package com.example.firstcode.chapter11.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {

    private const val BASE_URL = "http://10.0.2.2/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun <T> create(clazz: Class<T>): T = retrofit.create(clazz)

    inline fun <reified T> create(): T = create(T::class.java)
}