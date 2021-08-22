package com.example.firstcode.chapter9.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.firstcode.R

class NotificationActivity : AppCompatActivity() {

    private val notificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannel()

            findViewById<Button>(R.id.sendNotice).setOnClickListener {
                showNotification()
            }

            findViewById<Button>(R.id.sendNoticeWithIntent).setOnClickListener {
                showNotificationWithIntent()
            }

            findViewById<Button>(R.id.sendNoticeWithCancel).setOnClickListener {
                showNotificationWithCancel()
            }

            findViewById<Button>(R.id.sendNoticeWithLongText).setOnClickListener {
                showNotificationWithLongText()
            }

            findViewById<Button>(R.id.sendNoticeWithBigPicture).setOnClickListener {
                showNotificationWithBigPicture()
            }

            findViewById<Button>(R.id.sendNoticeWithImportant).setOnClickListener {
                showNotificationWithImportant()
            }
        }
    }

    private fun showNotificationWithImportant() {
        val notification = NotificationCompat.Builder(this, "important")
            .setContentTitle("Important Notification")
            .setContentText("This is important notification")
            .setSmallIcon(R.drawable.small_icon)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon))
            .build()
        notificationManager.notify(6, notification)
    }

    private fun showNotificationWithBigPicture() {
        val notification = NotificationCompat.Builder(this, "normal")
            .setContentTitle("Big Picture Notification")
            .setContentText("This is big picture notification")
            .setStyle(NotificationCompat.BigPictureStyle()
                .bigPicture(BitmapFactory.decodeResource(resources, R.drawable.big_image)))
            .setSmallIcon(R.drawable.small_icon)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon))
            .build()
        notificationManager.notify(5, notification)
    }

    private fun showNotificationWithLongText() {
        val notification = NotificationCompat.Builder(this, "normal")
            .setContentTitle("Long Text Notification")
            .setStyle(NotificationCompat.BigTextStyle().bigText("This is long text notification".repeat(5)))
            .setSmallIcon(R.drawable.small_icon)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon))
            .build()
        notificationManager.notify(4, notification)
    }

    private fun showNotificationWithCancel() {
        val pendingIntent = PendingIntent.getActivity(
            this, 0,
            Intent(this, NotificationIntentActivity::class.java), 0)
        val notification = NotificationCompat.Builder(this, "normal")
            .setContentTitle("Cancel Notification")
            .setContentText("This notification can click cancel")
            .setSmallIcon(R.drawable.small_icon)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(3, notification)
    }

    private fun showNotificationWithIntent() {
        val pendingIntent = PendingIntent.getActivity(
            this, 0,
            Intent(this, NotificationIntentActivity::class.java), 0)
        val notification = NotificationCompat.Builder(this, "normal")
            .setContentTitle("Intent Notification")
            .setContentText("This notification can intent")
            .setSmallIcon(R.drawable.small_icon)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon))
            .setContentIntent(pendingIntent)
            .build()
        notificationManager.notify(2, notification)
    }

    private fun showNotification() {
        val notification = NotificationCompat.Builder(this, "normal")
            .setContentTitle("Normal Notification")
            .setContentText("This is normal notification")
            .setSmallIcon(R.drawable.small_icon)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon)).build()
        notificationManager.notify(1, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        val normalChannel = NotificationChannel("normal", "Normal", NotificationManager.IMPORTANCE_DEFAULT)
        val highChannel = NotificationChannel("important", "Important", NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(normalChannel)
        notificationManager.createNotificationChannel(highChannel)
    }
}