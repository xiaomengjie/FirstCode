package com.example.firstcode.chapter15.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.firstcode.chapter15.logic.Repository
import com.example.firstcode.chapter15.logic.model.Place

class PlaceViewModel: ViewModel() {

    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    val placeLiveData = Transformations.switchMap(searchLiveData){
        Repository.searchPlace(it)
    }

    fun searchPlaces(query: String){
        searchLiveData.value = query
    }
}