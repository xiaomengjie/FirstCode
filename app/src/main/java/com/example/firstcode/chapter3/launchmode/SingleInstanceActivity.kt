package com.example.firstcode.chapter3.launchmode

import android.os.Bundle
import android.util.Log
import com.example.firstcode.other.BaseActivity
import com.example.firstcode.R

class SingleInstanceActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_instance)
        Log.e(TAG, "onCreate: $taskId")
    }
}