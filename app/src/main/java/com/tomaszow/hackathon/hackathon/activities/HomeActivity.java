package com.tomaszow.hackathon.hackathon.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tomaszow.hackathon.hackathon.R;
import com.tomaszow.hackathon.hackathon.model.Measurement;
import com.tomaszow.hackathon.hackathon.services.GPSTracker;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String ID = "Id";
    private static final int PROGRESS = 0x1;
    private String userId;
    private ProgressBar mProgress;
    private int mProgressStatus = 0;
    GPSTracker mGPS;

    //private ProgressBar mProgressBar;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getArguments();
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        mProgress.setOnClickListener(this);
        getLocation();

    }

    private void getArguments() {
        Intent myHomeIntent = getIntent();
        userId = myHomeIntent.getStringExtra(ID);
    }

    private void getLocation() {
        mGPS = new GPSTracker(HomeActivity.this);

        // check if GPS enabled
        if (mGPS.canGetLocation()) {

            double latitude = mGPS.getLatitude();
            double longitude = mGPS.getLongitude();
            sendCordsToServer(latitude, longitude);
            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            mGPS.showSettingsAlert();
        }
    }

    private void sendCordsToServer(double latitude, double longitude) {
        Measurement newMeasurment = new Measurement();
        newMeasurment.setLatitude(latitude);
        newMeasurment.setLongitude(longitude);
        newMeasurment.saveInBackground();
    }

    private void openMapTrackerActivity() {
        Intent mapTrackerIntent = new Intent(getApplicationContext(), ClusterMarkerActivity.class);
        startActivity(mapTrackerIntent);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.progressBar:
                openMapTrackerActivity();
                break;
        }
    }
}
