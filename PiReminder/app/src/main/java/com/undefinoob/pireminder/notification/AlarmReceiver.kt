package com.undefinoob.pireminder.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.undefinoob.pireminder.FileManager

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        FileManager.log(context, " AlarmReceiver: intent received.")
        TimerService.startActionNOTIFY(context)
    }

}