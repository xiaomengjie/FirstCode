package com.example.firstcode.chapter13.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(var name: String, var age: Int){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}