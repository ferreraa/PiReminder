package com.undefinoob.pireminder.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.undefinoob.pireminder.PIAppManager

class NotificationTappedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, p1: Intent?) {
        println("hello")
        NotificationUtils.setAlarm(context)
        PIAppManager.openPIApp(context)
    }
}