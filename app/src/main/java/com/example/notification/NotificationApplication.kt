package com.example.notification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager

class NotificationApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        val notificationChannel = NotificationChannel(
            "water_reminder",
            "water reminder channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )

        notificationChannel.description = "here is the description"

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)


    }
}
