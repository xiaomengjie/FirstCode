package com.example.firstcode.chapter6.offline

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import com.example.firstcode.other.ActivityCollector
import com.example.firstcode.other.actionStart

class ForceOfflineReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        AlertDialog.Builder(context)
            .setTitle("Warning")
            .setMessage("You are forced to be offline. Please try to login again.")
            .setCancelable(false)
            .setPositiveButton("OK"){_, _ ->
                ActivityCollector.finishAll()
                actionStart(context, LoginActivity::class.java)
            }
            .show()
    }
}