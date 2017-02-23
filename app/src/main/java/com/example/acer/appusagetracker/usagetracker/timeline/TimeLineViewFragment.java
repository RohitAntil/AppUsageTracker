package com.example.acer.appusagetracker.usagetracker.timeline;

;import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.appusagetracker.R;
import com.example.acer.appusagetracker.usagetracker.timeline.UsageEventsItem;
import com.example.acer.appusagetracker.usagetracker.timeline.TimeLineAdapter;
import com.example.acer.appusagetracker.usagetracker.timeline.TimeLineModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Acer on 1/25/2017.
 */
public class TimeLineViewFragment extends Fragment {

    List<UsageEventsItem> timeline;
    private RecyclerView mRecyclerView;
    private TimeLineAdapter mTimeLineAdapter;

    private List<TimeLineModel> mDataList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_timeline, container, false);
    }

    @Override
    public void onViewCreated(View rootView, Bundle savedInstanceState) {

        mRecyclerView=(RecyclerView) rootView.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        initView();
    }

    private void initView() {
        timeline=getTimeStamps();
        mTimeLineAdapter = new TimeLineAdapter(timeline,getContext());
        mRecyclerView.setAdapter(mTimeLineAdapter);
    }

    private List<UsageEventsItem> getTimeStamps()
    { int interval = UsageStatsManager.INTERVAL_DAILY;
        Date endTime=new Date();
        Date startTime =trim(endTime);
        UsageStatsManager usageStatsMgr = (UsageStatsManager) getActivity().getSystemService(Context.USAGE_STATS_SERVICE);
        UsageEvents events = usageStatsMgr.queryEvents(startTime.getTime(), endTime.getTime());
        List<UsageEventsItem> results = new ArrayList<>();
        UsageEvents.Event event = new UsageEvents.Event();
        PackageManager pm = getActivity().getPackageManager();
        UsageEvents.Event start = new UsageEvents.Event();
        events.getNextEvent(event);
        UsageEvents.Event last = new UsageEvents.Event();
        while (events.getNextEvent(event)) {
            if(!event.getPackageName().equalsIgnoreCase(start.getPackageName())) {
                UsageEventsItem item = new UsageEventsItem();
                item.pkgName = start.getPackageName();
                item.className = start.getClassName();
                item.type = start.getEventType();
                item.timeStamp = start.getTimeStamp();
                item.appName = item.pkgName;
                events.getNextEvent(event);
                item.totalTimeInForeground=last.getTimeStamp()- item.timeStamp;
                try {
                    item.appName = pm.getApplicationInfo(item.pkgName, 0).loadLabel(pm).toString();
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                results.add(item);
                start=event;
            }else
            {
                last=event;
            }
        }
        Collections.sort(results, new UsageEventsItem.UsageTimeComparator());
        return results;
    }
    public static Date trim(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return calendar.getTime();
    }
}
