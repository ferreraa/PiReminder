package com.undefinoob.pireminder.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.undefinoob.pireminder.FileManager

class BootReceiver : BroadcastReceiver()  {
    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action) {
            Intent.ACTION_BOOT_COMPLETED -> {
                val time = FileManager.readTime(context)
                NotificationUtils.setAlarm(context, time)
            }
        }
    }
}