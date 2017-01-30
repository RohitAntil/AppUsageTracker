package com.example.acer.appusagetracker.usagetracker.timeline;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.appusagetracker.R;
import com.example.acer.appusagetracker.usagetracker.UsageEventsItem;
import com.vipul.hp_hp.timelineview.TimelineView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Acer on 1/28/2017.
 */
public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder>  {

    private List<UsageEventsItem> mFeedList;
    private Context mContext;

    public TimeLineAdapter(List<UsageEventsItem> feedList, Context context) {
        mFeedList = feedList;
        mContext=context;
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position,getItemCount());
    }

    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View  view = View.inflate(parent.getContext(), R.layout.item_timeline, null);

        return new TimeLineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {

        final UsageEventsItem timeLineModel = mFeedList.get(position);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date(timeLineModel.timeStamp));
        holder.name.setText(timeLineModel.appName);
        holder.time.setText(currentDateTimeString);
        Drawable appIcon;
        try {
             appIcon = mContext.getPackageManager()
                    .getApplicationIcon(timeLineModel.pkgName);
        }catch (PackageManager.NameNotFoundException e) {
            appIcon = mContext.getDrawable(R.drawable.ic_default_app_launcher);
        }
        holder.icon.setImageDrawable(appIcon);

      //  holder.mTimelineView.setMarker(appIcon);


    }

    @Override
    public int getItemCount() {
        return (mFeedList!=null? mFeedList.size():0);
    }

    static public class TimeLineViewHolder extends RecyclerView.ViewHolder {
        public TimelineView mTimelineView;
        public TextView name;
        public TextView time;
        public ImageView icon;
        public TimeLineViewHolder(View itemView, int viewType) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tx_name);
            time= (TextView) itemView.findViewById(R.id.tx_time);
            icon=(ImageView) itemView.findViewById(R.id.icon) ;
            mTimelineView = (TimelineView) itemView.findViewById(R.id.time_marker);
            mTimelineView.initLine(viewType);
        }
    }


}
