package com.example.firstcode.chapter11.retrofit

import com.example.firstcode.chapter11.network.NodeBean
import retrofit2.Call
import retrofit2.http.GET

interface AppService {

    @GET("get_data.json")
    fun getAppData(): Call<List<NodeBean>>
}