package com.example.acer.appusagetracker.lockscreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by rohit02.kumar on 2/21/2017.
 */
public class PhoneBootReciever extends BroadcastReceiver {
/* Recieves events when phone boot is completed */
        @Override
        public void onReceive(Context context, Intent intent) {

            Intent myIntent = new Intent(context,UnlockCountService.class);
            context.startService(myIntent);
          //  context.bindService(new Intent(context, UnlockCountService.class),mServiceConnection, context.BIND_AUTO_CREATE);

        }
    }

