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
public class UpcomingDate {
    @SerializedName("startDate")
    String startDate;
    @SerializedName("endDate")
    String endDate;

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
