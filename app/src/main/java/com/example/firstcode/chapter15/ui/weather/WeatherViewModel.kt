package com.example.firstcode.chapter15.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.firstcode.chapter15.logic.Repository
import com.example.firstcode.chapter15.logic.model.Location
import com.example.firstcode.chapter15.logic.model.Weather

class WeatherViewModel: ViewModel() {

    private val locationLivedata = MutableLiveData<Location>()

    var locationLng = ""
    var locationLat = ""
    var placeName = ""

    val weatherLivedata: LiveData<Result<Weather>> = Transformations.switchMap(locationLivedata){
        Repository.refreshWeather(it.lng, it.lat)
    }

    fun refreshWeather(lng: String, lat: String){
        locationLivedata.value = Location(lng, lat)
    }
}