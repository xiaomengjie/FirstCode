package com.example.firstcode.chapter6.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.firstcode.other.showToast

class OrderedOneReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        showToast(context, "receiver one has received")
    }
}