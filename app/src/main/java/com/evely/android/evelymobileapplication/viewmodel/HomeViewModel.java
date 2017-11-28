package com.evely.android.evelymobileapplication.viewmodel;

import com.evely.android.evelymobileapplication.api.ApiClient;
import com.evely.android.evelymobileapplication.model.live.RetrievedEvents;

/**
 * Project SampleApiConnectionApp
 * Working on HomeViewModel
 * Created by Shion T. Fujie on 2017/11/13.
 */
public class HomeViewModel {
    private final ApiClient apiClient = new ApiClient();
    private final RetrievedEvents retrievedEvents = new RetrievedEvents(apiClient);

    public RetrievedEvents getRetrievedEvents() {
        return retrievedEvents;
    }
}
