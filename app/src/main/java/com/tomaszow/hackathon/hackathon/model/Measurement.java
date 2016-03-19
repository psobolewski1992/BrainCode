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

    public double getLongitude() {
        return getDouble("longitude");
    }

    public void setLatitude(double latitude) {
        put("latitude", latitude);
    }

    public double getLatitude() {
        return getDouble("latitude");
    }

    public void setPressure(double pressure) {
        put("pressure", pressure);
    }

    public double getPressure() {
        return getDouble("pressure");
    }

    public void setDistance(double distance) {
        put("distance", distance);
    }

    public double getDistance() {
        return getDouble("distance");
    }

    public void setCalories(double calories) {
        put("calories", calories);
    }

    public double getCalories() {
        return getDouble("calories");
    }

    public void setSugar(double sugar) {
        put("sugar", sugar);
    }

    public double getSugar() {
        return getDouble("sugar");
    }

    public void setUserID(String userID) {
        put("userID", userID);
    }

    public String getUserID() {
        return getString("userID");
    }

}
