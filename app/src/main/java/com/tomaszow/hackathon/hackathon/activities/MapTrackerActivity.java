package com.tomaszow.hackathon.hackathon.activities;

import com.tomaszow.hackathon.hackathon.R;
import com.tomaszow.hackathon.hackathon.adapter.customAdaper;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;


public class MapTrackerActivity extends Activity {
    public static String [] prgmNameList={"Owsian","Sobol","Mateusz"};
    public static String [] prgmActivityList={"Pierdzi","Niewiem","Brodacz"};
    ListView lv;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_tracker);
    lv = (ListView) findViewById(R.id.userList);
    lv.setAdapter(new customAdaper(this, prgmNameList, prgmActivityList));
    }

}
