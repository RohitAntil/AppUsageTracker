package com.example.acer.appusagetracker.usagetracker;

;import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.appusagetracker.R;
import com.example.acer.appusagetracker.usagetracker.timeline.TimeLineAdapter;
import com.example.acer.appusagetracker.usagetracker.timeline.TimeLineModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
        Calendar calendar = Calendar.getInstance();
        long endTime = calendar.getTimeInMillis();
        //calendar.add(Calendar.DAY_OF_MONTH,-1);
      //  endTime=calendar.getTimeInMillis();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        long startTime = calendar.getTimeInMillis();
        UsageStatsManager usageStatsMgr = (UsageStatsManager) getActivity().getSystemService(Context.USAGE_STATS_SERVICE);
        UsageEvents events = usageStatsMgr.queryEvents(startTime, endTime);
        List<UsageEventsItem> results = new ArrayList<>();
        UsageEvents.Event event = new UsageEvents.Event();
        PackageManager pm = getActivity().getPackageManager();
        while (events.getNextEvent(event)) {
            UsageEventsItem item = new UsageEventsItem();
            item.pkgName = event.getPackageName();
            item.className = event.getClassName();
            item.type = event.getEventType();
            item.timeStamp = event.getTimeStamp();
            item.appName = item.pkgName;
            try {
                item.appName = pm.getApplicationInfo(item.pkgName, 0).loadLabel(pm).toString();
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            results.add(item);
        }
        Collections.sort(results, new UsageEventsItem.UsageTimeComparator());
        return results;
    }
}
