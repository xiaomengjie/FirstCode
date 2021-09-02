package com.example.firstcode.chapter15.logic.model

data class Weather(
    val realtime: RealtimeResponse.Realtime,
    val daily: DailyResponse.Daily)
