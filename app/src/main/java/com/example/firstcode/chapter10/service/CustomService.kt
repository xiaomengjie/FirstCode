package com.example.firstcode.chapter10.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class CustomService : Service() {

    private val TAG: String = this.javaClass.simpleName

    private val downloadBinder by lazy { DownloadBinder() }

    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate: ")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand: ")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: ")
    }

    override fun onBind(intent: Intent): IBinder {
        Log.e(TAG, "onBind: ")
        return downloadBinder
    }

    inner class DownloadBinder: Binder(){
        fun startDownload(){
            Log.e(TAG, "startDownload: ")
        }

        fun getProgress(): Int{
            Log.e(TAG, "getProgress: ")
            return 0
        }
    }
}