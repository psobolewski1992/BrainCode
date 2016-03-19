package com.tomaszow.hackathon.hackathon.activities;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.tomaszow.hackathon.hackathon.R;
import com.tomaszow.hackathon.hackathon.adapter.customAdaper;
import com.tomaszow.hackathon.hackathon.model.ClusterMarkerLocation;
import com.tomaszow.hackathon.hackathon.model.Measurement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by mateusz on 19.03.2016.
 */
public class ClusterMarkerActivity extends BaseMapActivity {
    ListView lv;
    private double e=1;
    private double latitude;
    private double longitude;
    private ArrayList<Measurement> nearbyPeople = new ArrayList<>();
   /*s @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lv = (ListView) findViewById(R.id.userList);
        Bundle b = getIntent().getExtras();
        latitude = b.getDouble("latitude");
        longitude = b.getDouble("longitude");
        lv.setAdapter(new customAdaper(this, getListOfObjectFromQuery(nearbyPeople), getListOfObjectFromQuery(nearbyPeople)));
    }*/
        @Override
    protected void initMapSettings() {
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
            //lv = (ListView) findViewById(R.id.userList);
            //Bundle b = getIntent().getExtras();
            //latitude = b.getDouble("latitude");
            //longitude = b.getDouble("longitude");
            //lv.setAdapter(new customAdaper(this, getListOfObjectFromQuery(nearbyPeople), getListOfObjectFromQuery(nearbyPeople)));
    }


    @Override
    protected void initMapIfNecessary() {
        super.initMapIfNecessary();
        initMarkers();
    }

    private void initMarkers() {
        ClusterManager<ClusterMarkerLocation> clusterManager = new ClusterManager<ClusterMarkerLocation>( this, mGoogleMap );
        mGoogleMap.setOnCameraChangeListener(clusterManager);

        double lat;
        double lng;
        Random generator = new Random();
        for( int i = 0; i < 1000; i++ ) {
            lat = generator.nextDouble() / 3;
            lng = generator.nextDouble() / 3;
            if( generator.nextBoolean() ) {
                lat = -lat;
            }
            if( generator.nextBoolean() ) {
                lng = -lng;
            }
            clusterManager.addItem( new ClusterMarkerLocation( new LatLng( mCenterLocation.latitude + lat, mCenterLocation.longitude + lng ) ) );
        }
    }


}