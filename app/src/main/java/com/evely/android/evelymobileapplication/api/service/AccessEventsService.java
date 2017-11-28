package com.evely.android.evelymobileapplication.api.service;

import com.evely.android.evelymobileapplication.model.EventModel;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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
    Observable<List<EventModel>> retrieveEvents(@Query("limit") int limit, @Query("offset") int offset);
}
