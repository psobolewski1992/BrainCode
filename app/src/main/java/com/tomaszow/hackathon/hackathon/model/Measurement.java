package com.tomaszow.hackathon.hackathon.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by mateusz on 18.03.2016.
 */
@ParseClassName("Measurement")
public class Measurement extends ParseObject {

    public Measurement() {
        super();
    }

    public void setLongitude(double longitude) {
        put("longitude", longitude);
    }

    public void setLatitude(double latitude) {
        put("latitude", latitude);
    }

    public void setPressure(double pressure) {
        put("pressure", pressure);
    }

    public void setDistance(double distance) {
        put("distance", distance);
    }

    public void setCalories(double calories) {
        put("calories", calories);
    }

    public void setSugar(double sugar) {
        put("sugar", sugar);
    }

    public void setUserID(String userID) {
        put("userID", userID);
    }

}
