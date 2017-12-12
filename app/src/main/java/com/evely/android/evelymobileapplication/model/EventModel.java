package com.evely.android.evelymobileapplication.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Isom on 2017/12/13.
 */
@Parcel
public class EventModel {
    @SerializedName("body")
    String body;
    @SerializedName("host")
    User host;
    @SerializedName("id")
    String id;
    @SerializedName("mail")
    String mail;
    @SerializedName("place")
    EventLocation place;
    @SerializedName("tel")
    String tel;
    @SerializedName("title")
    String title;
    @SerializedName("upcomingDate")
    UpcomingDate upcomingDate;
    @SerializedName("updateDate")
    String updateDate;
    @SerializedName("url")
    String url;

    public String getBody() {
        return body;
    }

    public User getHost() {
        return host;
    }

    public String getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public EventLocation getPlace() {
        return place;
    }

    public String getTel() {
        return tel;
    }

    public String getTitle() {
        return title;
    }

    public UpcomingDate getUpcomingDate() {
        return upcomingDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public String getUrl() {
        return url;
    }
}
