package com.example.android_labs

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificationHelper(var co:Context, var msg:String) {
    private val CHANNEL_ID = "message id"
    private val NOTIFICATION_ID = 123
    @SuppressLint("UnspecifiedImmutableFlag")
    fun Notification(){
        createNotificationChannel()
        val setInt = Intent(co, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingInt = PendingIntent.getActivities(
            co,
            0,
            arrayOf(setInt),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
//        val icon = BitmapFactory.decodeResource(co.resources, R.drawable)
        val isNotification = NotificationCompat.Builder(co, CHANNEL_ID)
            .setSmallIcon(R.drawable.test)
            .setLargeIcon(BitmapFactory.decodeResource(co.resources, R.drawable.test))
            .setContentTitle("Notify")
            .setContentText(msg)
            .setContentIntent(pendingInt)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        println(isNotification)
        NotificationManagerCompat.from(co)
            .notify(NOTIFICATION_ID, isNotification)

    }
    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = CHANNEL_ID
            var descrip = "Channel descrip"
            val imports = NotificationManager.IMPORTANCE_DEFAULT
            var channels = NotificationChannel(CHANNEL_ID, name, imports).apply {
                description = descrip
            }
            var notificationManger = co.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManger.createNotificationChannel(channels)

        }
    }
}