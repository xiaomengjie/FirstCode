package com.example.firstcode.chapter3.launchmode

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.firstcode.other.BaseActivity
import com.example.firstcode.R

class StandardActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standard)

        Log.i(TAG, "onCreate: $this")
        findViewById<Button>(R.id.intent).setOnClickListener {
            startActivity(Intent(this, StandardActivity::class.java))
        }

        findViewById<Button>(R.id.singleTask).setOnClickListener {
            startActivity(Intent(this, SingleTaskActivity::class.java))
        }
    }
}