package com.example.firstcode.chapter10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.firstcode.R
import com.example.firstcode.chapter10.service.ServiceActivity
import com.example.firstcode.chapter10.thread.ThreadActivity
import com.example.firstcode.other.actionStart

class ServicePracticeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_practice)

        findViewById<Button>(R.id.createThread).setOnClickListener {
            actionStart(this, ThreadActivity::class.java)
        }

        findViewById<Button>(R.id.service).setOnClickListener {
            actionStart(this, ServiceActivity::class.java)
        }
    }
}