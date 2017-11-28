package com.evely.android.evelymobileapplication.model;

import com.google.gson.annotations.SerializedName;
import org.parceler.Parcel;

/**
 * Project SampleApiConnectionApp
 * Working on EventModel
 * Created by Shion T. Fujie on 2017/11/13.
 */
@Parcel
public class EventModel {
    @SerializedName("id")
    int id;
    @SerializedName("title")
    String title;
    @SerializedName("category")
    String category;
    @SerializedName("description")
    String description;

    // empty constructor needed by the Parceler library
    public EventModel(){}

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}
