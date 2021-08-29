package com.example.firstcode.chapter13.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firstcode.R

class LifecycleObserverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle_observer)
        lifecycle.addObserver(DataLifecycle(lifecycle))
    }
}