package com.tomaszow.hackathon.hackathon.application;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.tomaszow.hackathon.hackathon.fetcher.LruBitMapCache;
import com.tomaszow.hackathon.hackathon.fetcher.OkHttpStack;

/**
 * Created by mateusz on 18.03.2016.
 */
public class BrainCodeApplication extends Application {

    private static final Object TAG = BrainCodeApplication.class.getClass().getName();
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static BrainCodeApplication sBrainCodeApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        sBrainCodeApplication = this;
    }

    public static synchronized BrainCodeApplication getInstance() { return sBrainCodeApplication; }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext(), new OkHttpStack());
        }
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue, new LruBitMapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setRetryPolicy(new DefaultRetryPolicy(40 * 1000, 1, 1.0f)); //Request timeout and retry policy
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
