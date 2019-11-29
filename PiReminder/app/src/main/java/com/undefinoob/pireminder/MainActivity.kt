package com.undefinoob.pireminder

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.undefinoob.pireminder.notification.NotificationUtils
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateView()

        val notifier = findViewById<Button>(R.id.alarm)
        notifier.setOnClickListener{

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                //NotificationUtils.setAlarm(this, Calendar.getInstance().timeInMillis + 5000)
                val dialog = TimePickerManager(this)
                dialog.timePickedCallback = { updateView()}
                dialog.show()
            } else {
                NotificationUtils.setAlarm(this)
            }
            updateView()
        }

        val openPI = findViewById<Button>(R.id.button_open_pi)
        openPI.setOnClickListener {
            PIAppManager.openPIApp(this)
        }
    }

    override fun onResume() {
        super.onResume()
        updateView()
    }

    @SuppressLint("SimpleDateFormat")
    private fun updateView() {
        val text = findViewById<TextView>(R.id.alarm_date)

        if(NotificationUtils.isAlarmSet(this)) {
            val time = FileManager.readTime(this)
            val df = SimpleDateFormat("EEEE à HH:mm:ss")
            text.text = df.format(Date(time))
        } else {
            text.text = "Pas d'alarme"
        }
    }

}
