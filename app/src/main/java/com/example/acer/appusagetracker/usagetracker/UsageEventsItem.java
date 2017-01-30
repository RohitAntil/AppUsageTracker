package com.example.acer.appusagetracker.usagetracker;

import android.icu.text.SimpleDateFormat;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by Acer on 1/27/2017.
 */
public class UsageEventsItem {
    public String pkgName;
    public String className;
    public  int type;
    public  long timeStamp;
    public String appName;

    @Override
    public String toString() {
        Date date=new Date(timeStamp);
        return " appName=" + appName +" timeStamp=" + date;
    }

    static class UsageTimeComparator implements Comparator<UsageEventsItem>{

        @Override
        public int compare(UsageEventsItem t, UsageEventsItem t1) {

            return (int)(t1.timeStamp-t.timeStamp);
        }
    }
}
