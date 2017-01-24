package com.example.acer.appusagetracker.database;

/**
 * Created by Acer on 1/11/2017.
 */
public class Status {
    private String mAppName;
    private  String mAppPackage;
    private long mTime;

    public Status(String mAppName, String mAppPackage, long mTime) {
        this.mAppName = mAppName;
        this.mAppPackage = mAppPackage;
        this.mTime = mTime;
    }

    public String getmAppName() {
        return mAppName;
    }

    public String getmAppPackage() {
        return mAppPackage;
    }

    public long getmTime() {
        return mTime;
    }
}
