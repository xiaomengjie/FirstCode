package com.example.firstcode.chapter10.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.firstcode.R
import kotlin.concurrent.thread

class ForegroundService : Service() {

    private val TAG: String = this.javaClass.simpleName

    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate: ")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(NotificationChannel(
                "foreground_service", "ForegroundService", NotificationManager.IMPORTANCE_DEFAULT
            ))
        }
        val intent = PendingIntent.getActivity(this, 0, Intent(
            this, ServiceActivity::class.java
        ), 0
        )
        startForeground(1, NotificationCompat.Builder(this, "foreground_service")
            .setContentTitle("Foreground Notification")
            .setContentText("This is foreground service")
            .setContentIntent(intent)
            .setSmallIcon(R.drawable.small_icon)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon)).build())
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand: ")
        thread {
            Thread.sleep(3000)
            stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: ")
        stopForeground(true)
    }
}