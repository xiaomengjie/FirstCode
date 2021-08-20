package com.example.firstcode.other

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences

fun main() {
    /**
     * 简化SharedPreferences写法
     * 定义高阶函数 put
      */
    Activity().getPreferences(Context.MODE_PRIVATE).put {
        putString("name", "data")
    }

    /**
     * 简化ContentValues实例的获取
     */
    getContentValues("one" to 1, "two" to 10.5)
}

inline fun SharedPreferences.put(block: SharedPreferences.Editor.() -> Unit){
    val editor = edit()
    editor.block()
    editor.apply()
}

fun getContentValues(vararg pairs: Pair<String, Any?>) = ContentValues().apply {
    for ((key, value) in pairs){
        when(value){
            is Int -> put(key, value)
            is Long -> put(key, value)
            is Short -> put(key, value)
            is Float -> put(key, value)
            is Double -> put(key, value)
            is Boolean -> put(key, value)
            is String -> put(key, value)
            is Byte -> put(key, value)
            null -> putNull(key)
        }
    }
}
