package com.example.firstcode.chapter6.offline

import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firstcode.other.ActivityCollector

open class ForcedOfflineActivity: AppCompatActivity() {

    private val forceOfflineReceiver by lazy { ForceOfflineReceiver() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCollector.addActivity(this)
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(forceOfflineReceiver, IntentFilter("$packageName.forceOffline"))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(forceOfflineReceiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }
}