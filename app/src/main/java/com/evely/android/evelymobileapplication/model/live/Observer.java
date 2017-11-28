package com.evely.android.evelymobileapplication.model.live;

/**
 * Project SampleGeoApplication1
 * Working on Observer
 * Created by Shion T. Fujie on 2017/11/07.
 */
public interface Observer<T> {
    /**
     * @param v the new value
     */
    void onChanged(T v);
}
