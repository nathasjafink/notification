package com.example.notification

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

@Composable
fun MyNotification() {
    val context = LocalContext.current
    val notificationManager = NotificationManagerCompat.from(context)

    // Create a notification channel (only once)
    val channelId = "my_channel_id"
    val channelName = "My Channel"
    val importance = NotificationManager.IMPORTANCE_DEFAULT
    val channel = NotificationChannel(channelId, channelName, importance)
    notificationManager.createNotificationChannel(channel)

    // Check if POST_NOTIFICATIONS permission is granted
    val permission = "android.permission.POST_NOTIFICATIONS"
    val requestCode = 123

    if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
        // Permission already granted, proceed with notification creation
        BuildAndShowNotification()
    } else {
        // Request permission
        ActivityCompat.requestPermissions(context as Activity, arrayOf(permission), requestCode)
    }
}

// Handle the result in onRequestPermissionsResult
override fun OnRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<String>,
    grantResults: IntArray
) {
    if (requestCode == 123) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission granted, proceed with notification creation
            BuildAndShowNotification()
        } else {
            // Permission denied, handle accordingly (e.g., show a message)
        }
    }
}

private fun BuildAndShowNotification() {
    val context = LocalContext.current
    val notificationManager = NotificationManagerCompat.from(context)

    // Build the notification (same as before)
    val notification = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.ic_notification)
        .setContentTitle("My Notification")
        .setContentText("This is a basic notification.")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .build()

    // Show the notification
    val notificationId = 1 // Unique ID for this notification
    notificationManager.notify(notificationId, notification)
}
