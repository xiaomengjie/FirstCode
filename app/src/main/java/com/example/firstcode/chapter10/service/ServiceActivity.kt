package com.example.firstcode.chapter10.service

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import com.example.firstcode.R

class ServiceActivity : AppCompatActivity() {

    private lateinit var downloadBinder: CustomService.DownloadBinder

    private val serviceConnection = object : ServiceConnection{
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            downloadBinder = service as CustomService.DownloadBinder
            downloadBinder.startDownload()
            downloadBinder.getProgress()
        }

        override fun onServiceDisconnected(name: ComponentName) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        findViewById<Button>(R.id.startService).setOnClickListener {
            startService(Intent(this, CustomService::class.java))
        }

        findViewById<Button>(R.id.stopService).setOnClickListener {
            stopService(Intent(this, CustomService::class.java))
        }

        findViewById<Button>(R.id.bindService).setOnClickListener {
            bindService(Intent(this, CustomService::class.java),
                serviceConnection, Service.BIND_AUTO_CREATE)
        }

        findViewById<Button>(R.id.unbindService).setOnClickListener {
            unbindService(serviceConnection)
        }

        findViewById<Button>(R.id.foreground).setOnClickListener {
            startService(Intent(this, ForegroundService::class.java))
        }
    }
}