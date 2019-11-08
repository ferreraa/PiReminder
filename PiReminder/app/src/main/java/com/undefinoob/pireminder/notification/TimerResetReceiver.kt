package com.undefinoob.pireminder.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class TimerResetReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        NotificationUtils.setAlarm(context)
    }
}