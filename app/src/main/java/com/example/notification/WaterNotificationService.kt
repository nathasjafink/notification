package com.example.notification

import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import kotlin.random.Random

class WaterNotificationService(
    private val context: Context
) {
    private val notificationManager = context.getSystemService(NotificationManager::class.java)
    fun showBasicNotification() {
        val notification = NotificationCompat.Builder(context, "water_reminder")
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("My Notification")
            .setContentText("This is a basic notification.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }

    fun showExpandableNotification() {
        val image = context.bitmapFromResource(R.drawable.ic_notification)

        val notification = NotificationCompat.Builder(context, "water_reminder")
            .setContentTitle("Water Reminder")
            .setContentText("Time to drink some water!")
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setLargeIcon(image)
            .setStyle(
                NotificationCompat
                    .BigPictureStyle()
                    .bigPicture(image)
                    .bigLargeIcon(null as Bitmap?)
            )
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }

    fun showExpandableLongText() {
        val notification = NotificationCompat.Builder(context, "water_reminder")
            .setContentTitle("Water Reminder")
            .setContentText("Time to drink some water!")
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(
                NotificationCompat
                    .BigTextStyle()
                    .bigText("Very big text")
            )
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }

    fun showInboxStyleNotification() {
        val notification = NotificationCompat.Builder(context, "water_reminder")
            .setContentTitle("Water Reminder")
            .setContentText("Time to drink some water!")
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(
                NotificationCompat
                    .InboxStyle()
                    .addLine("Line 1")
                    .addLine("Line 2")
                    .addLine("Line 3")
                    .addLine("Line 4")
                    .addLine("Line 5")
                    .addLine("Line 6")
                    .addLine("Line 7")
            )
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }

    fun showNotificationGroup() {
        val groupId = "water_group"
        val summaryId = 0

        val notification1 = NotificationCompat.Builder(context, "water_reminder")
            .setContentTitle("Water Reminder")
            .setContentText("Time to drink some water!")
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(
                NotificationCompat
                    .InboxStyle()
                    .addLine("Line 1")
            )
            .setAutoCancel(true)
            .setGroup(groupId)
            .build()

        val notification2 = NotificationCompat.Builder(context, "water_reminder")
            .setContentTitle("Water Reminder")
            .setContentText("Time to drink some water!")
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(
                NotificationCompat
                    .InboxStyle()
                    .addLine("Line 1")
                    .addLine("Line 2")
            )
            .setAutoCancel(true)
            .setGroup(groupId)
            .build()

        val summaryNotification = NotificationCompat.Builder(context, "water_reminder")
            .setContentTitle("Water Reminder")
            .setContentText("Time to drink some water!")
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(
                NotificationCompat
                    .InboxStyle()
                    .setSummaryText("Water reminders missed")
                    .setBigContentTitle("Water Reminders")
            )
            .setAutoCancel(true)
            .setGroup(groupId)
            .setGroupSummary(true)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification1
        )
        notificationManager.notify(
            Random.nextInt(),
            notification2
        )
        notificationManager.notify(
            Random.nextInt(),
            summaryNotification
        )
    }

    private fun Context.bitmapFromResource(
        @DrawableRes resId: Int
    ) = BitmapFactory.decodeResource(
        resources,
        resId
    )
}