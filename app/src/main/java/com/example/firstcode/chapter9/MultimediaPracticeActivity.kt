package com.example.firstcode.chapter9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.firstcode.R
import com.example.firstcode.chapter9.camera.CameraActivity
import com.example.firstcode.chapter9.notification.NotificationActivity
import com.example.firstcode.other.actionStart

class MultimediaPracticeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multimedia_practice)

        findViewById<Button>(R.id.notification).setOnClickListener {
            actionStart(this, NotificationActivity::class.java)
        }

        findViewById<Button>(R.id.camera).setOnClickListener {
            actionStart(this, CameraActivity::class.java)
        }
    }
}