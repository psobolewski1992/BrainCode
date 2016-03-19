package com.tomaszow.hackathon.hackathon.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.tomaszow.hackathon.hackathon.R;
import com.tomaszow.hackathon.hackathon.adapter.customAdaper;


/**
 * Created by mateusz on 19.03.2016.
 */
public abstract class BaseMapActivity extends AppCompatActivity {

    protected LatLng mCenterLocation = new LatLng( 52.22, 21.10 );

    protected GoogleMap mGoogleMap;
    public static String [] prgmNameList={"Owsian","Sobol","Mateusz"};
    public static String [] prgmActivityList={"Pierdzi","Niewiem","Brodacz"};
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getMapLayoutId());
        initMapIfNecessary();
        lv = (ListView) findViewById(R.id.userList);
        lv.setAdapter(new customAdaper(this, prgmNameList, prgmActivityList));
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

    protected int getMapLayoutId() {
        return R.layout.activity_map_tracker;
    }

    protected float getInitialMapZoomLevel() {
        return 12.0f;
    }

    protected abstract void initMapSettings();
}