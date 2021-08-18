package com.example.firstcode.chapter6.register

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firstcode.R
import com.example.firstcode.chapter6.receiver.TimeChangeReceiver

class DynamicRegisterActivity : AppCompatActivity() {

    private val timeChangeReceiver: TimeChangeReceiver by lazy { TimeChangeReceiver() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_register)

        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_TIME_TICK)
        registerReceiver(timeChangeReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeReceiver)
    }
}