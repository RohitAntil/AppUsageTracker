package com.example.acer.appusagetracker.lockscreen;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

/**
 * Created by rohit02.kumar on 2/21/2017.
 */
public class UnlockCountService extends Service{

    @Override

    public int onStartCommand(Intent intent, int flags, int startId) {
        //TODO do something useful
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        BroadcastReceiver mReceiver = new UnlockReciever();
        registerReceiver(mReceiver, filter);
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        //TODO for communication return IBinder implementation
        return null;
    }
}
