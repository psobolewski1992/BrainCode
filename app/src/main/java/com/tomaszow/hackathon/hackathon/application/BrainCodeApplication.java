package com.tomaszow.hackathon.hackathon.application;

import android.app.Application;
import android.graphics.Typeface;

import com.parse.Parse;
import com.tomaszow.hackathon.hackathon.fetcher.Constants;

/**
 * Created by mateusz on 18.03.2016.
 */
public class BrainCodeApplication extends Application {

    private static final Object TAG = BrainCodeApplication.class.getClass().getName();
    private static BrainCodeApplication sBrainCodeApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        sBrainCodeApplication = this;
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                        .applicationId(Constants.AppID)
                        .clientKey(Constants.ClientKey)
                        .server("https://parseapi.back4app.com")
                        .build()
        );
    }

    public static synchronized BrainCodeApplication getInstance() { return sBrainCodeApplication; }


}
