package com.example.firstcode.chapter15.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.example.firstcode.chapter15.WeatherApplication
import com.example.firstcode.chapter15.logic.model.Place
import com.google.gson.Gson

object PlaceDao {

    fun savePlace(place: Place){
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavePlace(): Place{
        val placeString = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeString, Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences() =
        WeatherApplication.context.getSharedPreferences("weather_place", Context.MODE_PRIVATE)
}