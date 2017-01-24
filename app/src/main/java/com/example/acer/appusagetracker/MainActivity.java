package com.example.acer.appusagetracker;

import android.app.Activity;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.acer.appusagetracker.usagetracker.AppUsageStatisticsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        UsageList usage = new UsageList();
//        AppUsageStatisticsFragment fragment= AppUsageStatisticsFragment.newInstance();
//        fragmentTransaction.add(R.id.fragment_container,usage);
//        fragmentTransaction.commit();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, AppUsageStatisticsFragment.newInstance())
                    .commit();
        }
    }
}
