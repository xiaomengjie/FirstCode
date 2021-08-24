package com.example.firstcode.chapter10.service

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log

class CustomIntentService : IntentService("CustomIntentService") {

    private val TAG = this.javaClass.simpleName

    override fun onHandleIntent(intent: Intent?) {
        Log.e(TAG, "Thread name is: ${Thread.currentThread().name}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: ")
    }
}