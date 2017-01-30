package com.example.acer.appusagetracker.usagetracker.timeline;

import java.io.Serializable;

/**
 * Created by Acer on 1/28/2017.
 */
public class TimeLineModel implements Serializable {
    private String name;
    private int timeStamp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }
}
