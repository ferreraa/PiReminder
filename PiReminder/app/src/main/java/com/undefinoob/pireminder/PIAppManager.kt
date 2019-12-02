package com.undefinoob.pireminder

import android.content.Context
import android.content.Intent
import android.widget.Toast

class PIAppManager {

    companion object{
        private fun getLaunchIntent(context: Context): Intent? {
            return context.packageManager.getLaunchIntentForPackage("com.blockchainvault")
        }

        fun openPIApp(context: Context) {
            if(isPIInstalled(context))
                context.startActivity(getLaunchIntent(context))
            else
                Toast.makeText(context, "PI is not installed", Toast.LENGTH_SHORT).show();
        }

        private fun isPIInstalled(context: Context) : Boolean {
            return getLaunchIntent(context) != null
        }
    }
}