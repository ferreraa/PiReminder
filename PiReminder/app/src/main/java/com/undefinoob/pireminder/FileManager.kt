package com.undefinoob.pireminder

import android.content.Context
import java.io.*
import java.util.*


class FileManager {

    companion object {
        private const val fileName = "alarm_time"

        fun writeTime(calendar: Calendar, context: Context) {
            var file = File(context.filesDir, fileName)
            if (!file.exists()) {
                file.createNewFile()
            }

            val fos = context.openFileOutput(fileName, Context.MODE_PRIVATE)
            val os = ObjectOutputStream(fos)
            os.writeObject(calendar.timeInMillis)
            os.close()
            fos.close()
        }

        fun readTime(context: Context) : Long {
            val fis = context.openFileInput(fileName)
            val `is` = ObjectInputStream(fis)
            val time = `is`.readObject() as Long
            `is`.close()
            fis.close()
            return time
        }
    }
}