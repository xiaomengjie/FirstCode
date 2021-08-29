package com.example.firstcode.chapter13.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class DataLifecycle(val lifecycle: Lifecycle): LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
        println(lifecycle.currentState)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart(){
        println(lifecycle.currentState)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){
        println(lifecycle.currentState)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(){
        println(lifecycle.currentState)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop(){
        println(lifecycle.currentState)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(){
        println(lifecycle.currentState)
    }
}