package com.evely.android.evelymobileapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.evely.android.evelymobileapplication.api.ApiClient;
import com.evely.android.evelymobileapplication.api.service.AccessEventsService;
import com.evely.android.evelymobileapplication.model.EventModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Project SampleApiConnectionApp
 * Working on LiveEventsViewModel
 * Created by Shion T. Fujie on 2017/11/13.
 */
public class LiveEventsViewModel extends AndroidViewModel {
    private final LiveEvents liveEvents;

    public LiveEventsViewModel(@NonNull Application application) {
        super(application);

        final ApiClient apiClient = new ApiClient();
        liveEvents = new LiveEvents(apiClient);
    }

    public LiveEvents getLiveEvents() {
        return liveEvents;
    }

    public class LiveEvents extends LiveData<List<EventModel>> {
        private static final String TAG = "LiveEvents";
        private final ApiClient apiClient;

        public LiveEvents(@NonNull ApiClient apiClient) {
            this.apiClient = apiClient;
        }

        public void requestQuery(@Nullable String keyword, @Nullable String userId) {
            final AccessEventsService service = apiClient.get(AccessEventsService.class);

            service.listEvents(keyword,25, 0, userId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::postValue, throwable -> Log.d(TAG, throwable.getMessage()));
        }
    }
}
