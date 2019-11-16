package com.undefinoob.pireminder.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.undefinoob.pireminder.MainActivity
import com.undefinoob.pireminder.PIAppManager
import com.undefinoob.pireminder.R


class TimerNotification(private val context: Context) {
    private val channelName = "TimerNotification"
    private val builder : NotificationCompat.Builder


    init {

        createNotificationChannel()

        val tappedIntent = Intent(context, NotificationTappedReceiver::class.java)
        val tappedPendingIntent = PendingIntent.getBroadcast(context, 0 , tappedIntent,0)

        builder = NotificationCompat.Builder(context, channelName)
            .setSmallIcon(R.drawable.ic_stat_mine)
            .setContentTitle("PI TIME")
            .setContentText("Time to mine!")
            .setContentIntent(tappedPendingIntent)
            .setAutoCancel(true)
    }



    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = channelName
            val descriptionText = "test"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelName, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


    fun fireNotification() {
        with(NotificationManagerCompat.from(context)) {
            notify(0, builder.build())
            NotificationUtils.cancelAlarm(context)
        }
    }


}