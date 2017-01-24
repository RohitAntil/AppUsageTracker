package com.example.acer.appusagetracker.usagetracker;

/**
 * Created by Acer on 1/18/2017.
 */

        import android.app.Application;
        import android.content.Context;
        import android.content.pm.ApplicationInfo;
        import android.content.pm.PackageManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.example.acer.appusagetracker.R;

        import java.beans.IndexedPropertyChangeEvent;
        import java.text.DateFormat;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.List;

/**
 * Provide views to RecyclerView with the directory entries.
 */
public class UsageListAdapter extends RecyclerView.Adapter<UsageListAdapter.ViewHolder> {

    private List<CustomUsageStats> mCustomUsageStatsList = new ArrayList<>();
    private DateFormat mDateFormat = new SimpleDateFormat("hh:mm:ss");
    private Context context;
    UsageListAdapter(Context c)
    {
        context=c;
    }


    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mPackageName;
        private final TextView mLastTimeUsed;
        private final ImageView mAppIcon;
        private final TextView mPercentage;

        public ViewHolder(View v) {
            super(v);
            mPackageName = (TextView) v.findViewById(R.id.textview_package_name);
            mLastTimeUsed = (TextView) v.findViewById(R.id.textview_last_time_used);
            mAppIcon = (ImageView) v.findViewById(R.id.app_icon);
            mPercentage=(TextView) v.findViewById(R.id.percentage);
        }


        public TextView getLastTimeUsed() {
            return mLastTimeUsed;
        }

        public TextView getPackageName() {
            return mPackageName;
        }

        public ImageView getAppIcon() {
            return mAppIcon;
        }

        public TextView getPercentage() {
            return mPercentage;
        }
    }

    public UsageListAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.usage_row, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        PackageManager pm= context.getPackageManager();
        ApplicationInfo ai=null;
        try {
            ai=pm.getApplicationInfo(mCustomUsageStatsList.get(position).usageStats.getPackageName(), 0);

        }catch (final PackageManager.NameNotFoundException e) {
            ai = null;
        }
        final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");
        viewHolder.getPackageName().setText(applicationName);
        long lastTimeUsed = mCustomUsageStatsList.get(position).usageStats.getTotalTimeInForeground();
          viewHolder.getLastTimeUsed().setText((lastTimeUsed/1000)+"sec");
        viewHolder.getPercentage().setText("5 %");
       // viewHolder.getLastTimeUsed().setText(mDateFormat.format(new Date(lastTimeUsed/1000)));
        viewHolder.getAppIcon().setImageDrawable(mCustomUsageStatsList.get(position).appIcon);
    }

    @Override
    public int getItemCount() {
        return mCustomUsageStatsList.size();
    }

    public void setCustomUsageStatsList(List<CustomUsageStats> customUsageStats) {
        mCustomUsageStatsList = customUsageStats;
    }
}