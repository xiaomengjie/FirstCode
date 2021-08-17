package com.example.firstcode.chapter4.chat

data class Msg(val content: String, val type: Int){
    companion object{
        const val TYPE_RECEIVED = 1
        const val TYPE_SEND = 2
    }
}
