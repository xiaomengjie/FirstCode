package com.example.firstcode.chapter13.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object Repository {

    fun getUser(userId: Int): LiveData<User>{
        val liveData = MutableLiveData<User>()
        liveData.value = User(userId, "user")
        return liveData
    }

    fun refresh(): LiveData<Any> {
        return MutableLiveData()
    }
}

data class User(val userId: Int, val userName: String)