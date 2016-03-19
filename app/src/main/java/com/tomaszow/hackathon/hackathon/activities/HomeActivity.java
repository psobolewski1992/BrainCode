package com.tomaszow.hackathon.hackathon.activities;

import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tomaszow.hackathon.hackathon.R;

public class HomeActivity extends AppCompatActivity {
    private static final int PROGRESS = 0x1;

    private ProgressBar mProgress;
    private TextView mNumberOfFriends;
    private int mProgressStatus = 0;

    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        mNumberOfFriends = (TextView) findViewById(R.id.numberOfFriends);
//        Typeface custom_font = Typeface.createFromAsset(getAssets(), "font.ttf");
//        mNumberOfFriends.setTypeface(custom_font);

    }
}
