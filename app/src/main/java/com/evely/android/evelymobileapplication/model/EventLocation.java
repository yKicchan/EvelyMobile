package com.evely.android.evelymobileapplication.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Shion T. Fujie.
 * Project name: EvelyMobile
 * Date : 13 / 12 / 2017
 * Using : Android Studio
 */
@Parcel
public class EventLocation {
    @SerializedName("lat")
    double lat;
    @SerializedName("lng")
    double lng;
    @SerializedName("name")
    String name;

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getName() {
        return name;
    }
}
