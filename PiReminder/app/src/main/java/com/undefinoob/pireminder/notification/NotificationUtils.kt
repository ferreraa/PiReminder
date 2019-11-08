package com.undefinoob.pireminder.notification

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.undefinoob.pireminder.FileManager
import java.util.*

class NotificationUtils {




    companion object {
        private const val oneDayAsMillisec = 86400000

        private fun createPendingIntent(context: Context):PendingIntent {
            val alarmIntent = Intent(context, AlarmReceiver::class.java)
            return PendingIntent.getBroadcast(
                context,
                0,
                alarmIntent,
                PendingIntent.FLAG_CANCEL_CURRENT
            )
        }

        fun setAlarm(context: Context) {
            val alarmManager = context.getSystemService(Activity.ALARM_SERVICE) as AlarmManager
            val pendingIntent = createPendingIntent(context)

            val calendar = Calendar.getInstance()
            calendar.timeInMillis += oneDayAsMillisec

            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
            FileManager.writeTime(calendar, context)
        }

        fun isAlarmSet(context: Context) : Boolean {
            val alarmIntent = Intent(context, AlarmReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                alarmIntent,
                PendingIntent.FLAG_NO_CREATE
            )

            return pendingIntent != null
        }

        fun cancelAlarm(context: Context) {
            createPendingIntent(context).cancel()
        }

    }


}