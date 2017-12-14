package com.evely.android.evelymobileapplication.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Project EvelyMobileApplication
 * Working on User
 * Created by Shion T. Fujie on 2017/12/06.
 */
@Parcel
public class User {
    @SerializedName("id")
    String id;
    @SerializedName("name")
    String name = "Holly Azora Anforth";
    String profilePhotUrl = "http://160.16.140.145:8080/uploads/attachment/5a112b8294f8050056743e41/1bc055e9057ba267d43c272c55f8cd65.jpg";

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProfilePhotUrl() {
        return profilePhotUrl;
    }
}
