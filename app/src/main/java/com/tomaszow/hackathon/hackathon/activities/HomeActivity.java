package com.tomaszow.hackathon.hackathon.activities;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.tomaszow.hackathon.hackathon.R;

public class HomeActivity extends AppCompatActivity {
    private static final int PROGRESS = 0x1;

    private ProgressBar mProgress;
    private int mProgressStatus = 0;

    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);

    }
}
