package com.example.acer.appusagetracker.lockscreen;

/**
 * Created by rohit02.kumar on 1/30/2017.
 */
public class Lock  {
    String date;
    int count;

    public void setDate(String date) {
        this.date = date;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }
    public int getCount() {
        return count;
    }
}
