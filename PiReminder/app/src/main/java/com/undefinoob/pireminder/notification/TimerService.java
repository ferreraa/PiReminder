package com.undefinoob.pireminder.notification;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.os.VibrationEffect;
import android.os.Vibrator;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

public class TimerService extends JobIntentService {
    public static final int JOB_ID = 314; //PI :)

    public static void startActionNOTIFY(Context context) {
        enqueueWork(context, TimerService.class, JOB_ID, new Intent());
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createWaveform(
                   new long[]{200L,500L,500L,500L,500L,1000L},
                    new int[] {0,100,0,100,0,255},
                    -1));
        } //else, the vibration is handled within notification builder
        new TimerNotification(this).fireNotification();
    }
}
