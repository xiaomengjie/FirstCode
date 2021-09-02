package com.example.firstcode.chapter15.logic.network

import com.example.firstcode.chapter15.WeatherApplication
import com.example.firstcode.chapter15.logic.model.DailyResponse
import com.example.firstcode.chapter15.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    @GET("v2.5/${WeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng")lng: String, @Path("lat")lat: String): Call<RealtimeResponse>

    @GET("v2.5/${WeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng")lng: String, @Path("lat")lat: String): Call<DailyResponse>
}