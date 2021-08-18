package com.example.firstcode.chapter6.send

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.firstcode.R

class SendBroadcastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_broadcast)

        findViewById<Button>(R.id.sendStandard).setOnClickListener {
            sendBroadcast(Intent().apply {
                action = "$packageName.standard"
                //由于Android8.0以后静态接收者不能接收隐式广播
                //所以需要设置package指定发送给哪个程序
                `package` = packageName
            })
        }

        findViewById<Button>(R.id.sendOrdered).setOnClickListener {
            sendOrderedBroadcast(Intent().apply {
                action = "$packageName.ordered"
                //由于Android8.0以后静态接收者不能接收隐式广播
                //所以需要设置package指定发送给哪个程序
                `package` = packageName
            }, null)
        }
    }
}