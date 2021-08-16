package com.example.firstcode.chapter3.launchmode

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.firstcode.other.BaseActivity
import com.example.firstcode.R

class LaunchModeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_mode)

        findViewById<Button>(R.id.standard).setOnClickListener {
            startActivity(Intent(this, StandardActivity::class.java))
        }

        findViewById<Button>(R.id.singleTop).setOnClickListener {
            startActivity(Intent(this, SingleTopActivity::class.java))
        }

        findViewById<Button>(R.id.singleTask).setOnClickListener {
            startActivity(Intent(this, SingleTaskActivity::class.java))
        }

        findViewById<Button>(R.id.singleInstance).setOnClickListener {
            startActivity(Intent(this, SingleInstanceActivity::class.java))
        }

        Log.e(TAG, "onCreate: $taskId")
    }
}