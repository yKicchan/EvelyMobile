package com.evely.android.evelymobileapplication.model.live;

import java.util.ArrayList;
import java.util.List;

/**
 * Project SampleGeoApplication1
 * Working on LiveData
 * Created by Shion T. Fujie on 2017/11/07.
 */
public abstract class LiveData<T> {
    private T value;
    private List<Observer<T>> observerList = new ArrayList<>();

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
        for (Observer<T> observer : observerList) {
            observer.onChanged(value);
        }
    }

    public void observe(Observer<T> observer) {
        observerList.add(observer);
    }

    public void remove(Observer<T> observer) {
        observerList.remove(observer);
    }
}
