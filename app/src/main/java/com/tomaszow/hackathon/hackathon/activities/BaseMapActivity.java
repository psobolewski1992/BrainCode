package com.tomaszow.hackathon.hackathon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.tomaszow.hackathon.hackathon.R;
import com.tomaszow.hackathon.hackathon.adapter.customAdaper;
import com.tomaszow.hackathon.hackathon.model.Measurement;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mateusz on 19.03.2016.
 */
public abstract class BaseMapActivity extends AppCompatActivity {

    protected LatLng mCenterLocation = new LatLng( 52.22, 21.10 );

    protected GoogleMap mGoogleMap;
    ListView lv;
    private double e=1;
    private double latitude;
    private double longitude;
    private ArrayList<Measurement> nearbyPeople = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getMapLayoutId());
        initMapIfNecessary();
        lv = (ListView) findViewById(R.id.userList);
        Bundle b = getIntent().getExtras();
        latitude = b.getDouble("latitude");
        longitude = b.getDouble("longitude");
        lv.setAdapter(new customAdaper(this, getListOfObjectFromQuery(nearbyPeople), getListOfObjectFromQuery(nearbyPeople)));


    }
    private ArrayList<String> getListOfObjectFromQuery(ArrayList<Measurement> nearbyPeople){
        ArrayList<String> objects = new ArrayList<>();
        for(Measurement human : nearbyPeople){
            objects.add(human.getUserID());
        }
        return objects;
    }



    @Override
    protected void onResume() {
        super.onResume();
        initMapIfNecessary();
    }

    protected void initMapIfNecessary() {
        if( mGoogleMap != null ) {
            return;
        }

        mGoogleMap = ( (MapFragment) getFragmentManager().findFragmentById( R.id.map ) ).getMap();

        initMapSettings();
        initCamera();
    }

    protected void initCamera() {
        CameraPosition position = CameraPosition.builder()
                .target( mCenterLocation )
                .zoom( getInitialMapZoomLevel() )
                .build();

        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), null);
    }
    private void executeQuery() {
        double e;
        ParseQuery<Measurement> query = ParseQuery.getQuery(Measurement.class);
        query.whereGreaterThanOrEqualTo("latitude", latitude - 1);
        query.whereLessThanOrEqualTo("latitude", latitude + 1);
        query.whereLessThanOrEqualTo("longitude", longitude + 1);
        query.whereGreaterThanOrEqualTo("longitude", longitude - 1);
        query.findInBackground(new FindCallback<Measurement>() {
            public void done(List<Measurement> itemList, ParseException e) {
                if (e == null) {
                    // Access the array of results here
                    String firstItemId = itemList.get(0).getObjectId();
                    nearbyPeople.addAll(itemList);
                    //Toast.makeText(HomeActivity.this, firstItemId, Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("item", "Error: " + e.getMessage());
                }
            }
        });
    }
    protected int getMapLayoutId() {
        return R.layout.activity_map_tracker;
    }

    protected float getInitialMapZoomLevel() {
        return 12.0f;
    }

    protected abstract void initMapSettings();
}