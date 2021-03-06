package com.evely.android.evelymobileapplication.model.live;

import com.evely.android.evelymobileapplication.api.ApiClient;
import com.evely.android.evelymobileapplication.model.EventModel;
import com.evely.android.evelymobileapplication.api.service.AccessEventsService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

/**
 * Project SampleApiConnectionApp
 * Working on RetrievedEvents
 * Created by Shion T. Fujie on 2017/11/13.
 */
public class RetrievedEvents extends LiveData<List<EventModel>> {
    final ApiClient apiClient;

    public RetrievedEvents(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public void requestRetrieval() {
        final AccessEventsService service = apiClient.get(AccessEventsService.class);

        service.retrieveEvents(25, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setValue);
    }
}
