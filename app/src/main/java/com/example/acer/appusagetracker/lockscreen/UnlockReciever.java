package com.example.acer.appusagetracker.lockscreen;

import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.acer.appusagetracker.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rohit02.kumar on 1/30/2017.
 */
public class UnlockReciever extends BroadcastReceiver {
       static int count=0;
     private LockCountManager manager;
        @Override
        public void onReceive(Context context, Intent intent) {
            manager=new LockCountManager(context);
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            Date dateobj = new Date();
     //       System.out.println(df.format(dateobj));
            String date=df.format(dateobj);
            manager.open();
            int isExists=manager.isEntryExists(date);
            if(isExists!=-1)
            {
                manager.updateLockCount(date,isExists+1);

            }else {
                manager.createTableEntry(date,1);
                isExists=1;
            }
             manager.close();
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(context)
                                .setSmallIcon(R.drawable.ic_default_app_launcher)
                                .setContentTitle("App Usage")
                                .setContentText("Today device unlocked "+ isExists +" times ");

                NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(1, mBuilder.build());

           }

    }

