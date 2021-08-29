package com.example.firstcode.chapter13.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DataViewModel(countReserved: Int): ViewModel() {

    var counter = countReserved
}

class DataViewModelFactory(private val countReserved: Int): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DataViewModel(countReserved) as T
    }
}