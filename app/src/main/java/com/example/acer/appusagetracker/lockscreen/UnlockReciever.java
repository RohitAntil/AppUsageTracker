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

//            DateFormat df = new SimpleDateFormat("dd/MM/yy");
//            Date dateobj = new Date();
//            String date=df.format(dateobj);
//            /*checking into database whether todays date entry is created or not */
//            manager.open();
//            int isExists=manager.isEntryExists(date);
//            if(isExists!=-1)
//            {
//                manager.updateLockCount(date,isExists+1);
//
//            }else {
//                manager.createTableEntry(date,1);
//                isExists=1;
//            }
//             manager.close();
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(context)
                                .setSmallIcon(R.mipmap.app_icon)
                                .setContentTitle("App Usage")
                                .setContentText("Today device unlocked "+ ++count +" times ");

                NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(1, mBuilder.build());

           }

    }

