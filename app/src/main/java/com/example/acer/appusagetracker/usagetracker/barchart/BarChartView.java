package com.example.acer.appusagetracker.usagetracker.barchart;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.appusagetracker.R;
/**
 * Created by Acer on 1/25/2017.
 */
public class BarChartView extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_bar_chart, container, false);
        }
}
