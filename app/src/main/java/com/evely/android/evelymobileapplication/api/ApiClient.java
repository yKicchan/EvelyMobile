package com.evely.android.evelymobileapplication.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Project SampleApiConnectionApp
 * Working on ApiClient
 * Created by Shion T. Fujie on 2017/11/13.
 */
public class ApiClient {
    private Retrofit retrofit;
    private Map<Class, Object> interfaceCache = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> service) {
        T cachedInterface = (T) interfaceCache.get(service);
        if (cachedInterface == null) {
            cachedInterface = retrofit().create(service);
            interfaceCache.put(service, cachedInterface);
        }

        return cachedInterface;
    }

    private Retrofit retrofit() {
        if (retrofit == null) {
            final OkHttpClient client = new OkHttpClient.Builder().build();
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl("http://160.16.140.145:8888/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
