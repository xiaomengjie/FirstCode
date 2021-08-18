package com.example.firstcode.chapter6.offline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.firstcode.R

class LoginSuccessActivity : ForcedOfflineActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_success)

        findViewById<Button>(R.id.forceOffline).setOnClickListener {
            sendBroadcast(Intent().apply {
                action = "$packageName.forceOffline"
            })
        }
    }
}