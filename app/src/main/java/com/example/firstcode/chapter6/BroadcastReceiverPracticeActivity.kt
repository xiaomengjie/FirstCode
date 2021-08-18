package com.example.firstcode.chapter6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.firstcode.R
import com.example.firstcode.chapter6.register.DynamicRegisterActivity
import com.example.firstcode.chapter6.send.SendBroadcastActivity
import com.example.firstcode.other.actionStart

class BroadcastReceiverPracticeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_receiver_practice)

        findViewById<Button>(R.id.dynamicRegisterBroadcastReceiver).setOnClickListener {
            actionStart(this, DynamicRegisterActivity::class.java)
        }

        findViewById<Button>(R.id.sendBroadcast).setOnClickListener {
            actionStart(this, SendBroadcastActivity::class.java)
        }
    }

}