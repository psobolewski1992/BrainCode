package com.tomaszow.hackathon.hackathon.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.tomaszow.hackathon.hackathon.R;
import com.tomaszow.hackathon.hackathon.model.Measurement;
import com.tomaszow.hackathon.hackathon.services.GPSTracker;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String ID = "Id";
    private static final int PROGRESS = 0x1;
    private String userId;
    private ProgressBar mProgress;
    private int mProgressStatus = 0;
    GPSTracker mGPS;
    private double latitude;
    private double longitude;
    private ArrayList<Measurement> nearbyPeople = new ArrayList<>();
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
        executeQuery();

    }

    private void getArguments() {
        Intent myHomeIntent = getIntent();
        userId = myHomeIntent.getStringExtra(ID);
    }

    private void getLocation() {
        mGPS = new GPSTracker(HomeActivity.this);

        // check if GPS enabled
        if (mGPS.canGetLocation()) {

           latitude = mGPS.getLatitude();
           longitude = mGPS.getLongitude();
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
        Bundle b = new Bundle();
        b.putDouble("latitude", latitude);
        b.putDouble("longitude", longitude);
        mapTrackerIntent.putExtras(b);
        startActivity(mapTrackerIntent);
    }

    private void executeQuery() {
        double e;
        ParseQuery<Measurement> query = ParseQuery.getQuery(Measurement.class);
        query.whereGreaterThanOrEqualTo("latitude", latitude);
        query.whereLessThanOrEqualTo("latitude", latitude);
        query.whereLessThanOrEqualTo("longitude", longitude);
        query.whereGreaterThanOrEqualTo("longitude", longitude);
        query.findInBackground(new FindCallback<Measurement>() {
            public void done(List<Measurement> itemList, ParseException e) {
                if (e == null) {
                    // Access the array of results here
                    String firstItemId = itemList.get(0).getObjectId();
                    mProgressStatus=itemList.size();
                    nearbyPeople.addAll(itemList);
                   // mProgress.setVisibility(View.INVISIBLE);
                    //Toast.makeText(HomeActivity.this, firstItemId, Toast.LENGTH_SHORT).show();
                   } else {
                    Log.d("item", "Error: " + e.getMessage());
                }
            }
        });
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
