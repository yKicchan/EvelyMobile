package com.evely.android.evelymobileapplication.api.service;

import com.evely.android.evelymobileapplication.model.EventModel;

import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableAll;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

/**
 * Project SampleApiConnectionApp
 * Working on AccessEventsService
 * Created by Shion T. Fujie on 2017/11/13.
 */
public interface AccessEventsService {
    @Headers("Accept: application/vnd.events+json; type=collection")
    @GET("events")
    Observable<List<EventModel>> listEvents(@Query("keyword") String keyword, @Query("limit") Integer limit, @Query("offset") Integer offset, @Query("user_id") String user_id);

    @Headers("Accept: application/vnd.event+json")
    @GET("events/{user_id}/{event_id}")
    Observable<EventModel> showEvent(@Path("user_id") String user_id, @Path("event_id") String event_id);
}
