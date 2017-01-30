package com.example.acer.appusagetracker.lockscreen;

import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.acer.appusagetracker.R;

/**
 * Created by rohit02.kumar on 1/30/2017.
 */
public class UnlockReciever extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            KeyguardManager keyguardManager = (KeyguardManager)context.getSystemService(Context.KEYGUARD_SERVICE);
//            if (keyguardManager.isKeyguardSecure()) {
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(context)
                                .setSmallIcon(R.drawable.ic_default_app_launcher)
                                .setContentTitle("App Usage")
                                .setContentText("Phone Unlocked");

                NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(1, mBuilder.build());

    //        }
        }
    }

