package com.undefinoob.pireminder

import android.app.TimePickerDialog
import android.app.Dialog
import android.content.Context
import com.undefinoob.pireminder.notification.NotificationUtils
import java.util.*


class TimePickerManager(val context: Context) {

    var timePickedCallback: (()-> Unit)? = null
    private var dialog : Dialog
    private var timePickedListener : TimePickerDialog.OnTimeSetListener

    init {
        val c = Calendar.getInstance()
        val hr = c.get(Calendar.HOUR_OF_DAY)
        val min = c.get(Calendar.MINUTE)
        timePickedListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minutes ->
            val now = Calendar.getInstance()
            val timePicked = Calendar.getInstance()
            timePicked.set(Calendar.HOUR_OF_DAY, hourOfDay)
            timePicked.set(Calendar.MINUTE, minutes)
            timePicked.set(Calendar.SECOND, 0)
            val timeDiff =  timePicked.timeInMillis - now.timeInMillis
            if(timeDiff < 180_000L)
                timePicked.timeInMillis += 86400000L

            NotificationUtils.setAlarm(context, timePicked.timeInMillis)

            timePickedCallback?.invoke()
        }

        dialog = createdDialog(hr, min)

    }


    private fun createdDialog(hr: Int, min:Int): TimePickerDialog {
            return TimePickerDialog(context, timePickedListener
                , hr, min, true)
        }

    fun show() {
        dialog.show()
    }
}