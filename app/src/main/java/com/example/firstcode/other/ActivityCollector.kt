package com.example.firstcode.other

import android.app.Activity

object ActivityCollector {

    private val activityList = mutableListOf<Activity>()

    fun addActivity(activity: Activity){
        activityList.add(activity)
    }

    fun removeActivity(activity: Activity){
        activityList.remove(activity)
    }

    fun finishAll(){
        activityList.forEach {
            if (!it.isFinishing){
                it.finish()
            }
        }
        activityList.clear()
    }
}