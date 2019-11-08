package com.undefinoob.pireminder.notification;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

public class TimerService extends JobIntentService {
    public static final int JOB_ID = 314; //PI :)

    public static void startActionNOTIFY(Context context) {
        enqueueWork(context, TimerService.class, JOB_ID, new Intent());
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        new TimerNotification(this).fireNotification();
    }
}
