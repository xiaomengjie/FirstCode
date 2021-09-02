package com.example.firstcode.chapter15.logic

import androidx.lifecycle.liveData
import com.example.firstcode.chapter15.logic.network.WeatherNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.RuntimeException

object Repository {

    fun searchPlace(query: String) = liveData(Dispatchers.IO){
        val result = try {
            val placeResponse = WeatherNetwork.searchPlace(query)
            if (placeResponse.status == "ok"){
                val places = placeResponse.places
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        }catch (e: Exception){
            Result.failure(e)
        }
        emit(result)
    }
}