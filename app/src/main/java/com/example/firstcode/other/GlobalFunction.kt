package com.example.firstcode.other

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import org.json.JSONArray
import org.json.JSONObject
import org.w3c.dom.Node
import org.w3c.dom.NodeList

fun <T: Activity> actionStart(context: Context, clazz: Class<T>){
    context.startActivity(Intent(context, clazz))
}

fun showToast(context: Context, msg: String){
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

operator fun String.times(n: Int) = repeat(n)

infix fun Int.to(name: String) = ButtonBean(this, name)

inline fun <reified T: Activity> startActivity(context: Context){
    context.startActivity(Intent(context, T::class.java))
}

inline fun JSONArray.forEach(block: (JSONObject) -> Unit){
    for (i in 0 until length()){
        block(getJSONObject(i))
    }
}