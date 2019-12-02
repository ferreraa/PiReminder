package com.undefinoob.pireminder

import android.content.Context
import java.io.*
import java.text.SimpleDateFormat
import java.util.*


class FileManager {

    companion object {
        private const val fileName = "alarm_time"
        private const val logsFileName = "logs"


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

        fun log(context: Context, message: String) {
            val file = File(context.filesDir, logsFileName)
            if (!file.exists()) {
                file.createNewFile()
            }

            val fos = context.openFileOutput(logsFileName, Context.MODE_APPEND)
            val os = ObjectOutputStream(fos)
            var log : String = SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(Date())
            log += " $message\n"
            os.writeObject(log)
            os.close()
            fos.close()
        }

        fun clearLogs(context: Context) {
            val file = File(context.filesDir, logsFileName)
            if (!file.exists()) {
                file.delete()
            }
        }

        fun readLogs(context: Context) : String {
            return File(context.filesDir, logsFileName).readText()

/*            val fis = context.openFileInput(logsFileName)
            val `is` = ObjectInputStream(fis)
            val logs = `is`.read as String
            `is`.close()
            fis.close()
            return logs
*/
        }
    }
}