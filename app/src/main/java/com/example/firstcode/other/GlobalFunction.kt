package com.example.firstcode.other

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast

fun <T: Activity> actionStart(context: Context, clazz: Class<T>){
    context.startActivity(Intent(context, clazz))
}

fun showToast(context: Context, msg: String){
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

operator fun String.times(n: Int) = repeat(n)