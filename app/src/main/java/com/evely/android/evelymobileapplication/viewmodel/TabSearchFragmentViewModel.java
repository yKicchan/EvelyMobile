package com.evely.android.evelymobileapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.evely.android.evelymobileapplication.model.Category;
import com.evely.android.evelymobileapplication.model.EventModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shion T. Fujie.
 * Project name: EvelyMobile
 * Date : 14 / 12 / 2017
 * Using : Android Studio
 */

public class TabSearchFragmentViewModel extends AndroidViewModel {
    public TabSearchFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public List<Category> getCategories(){
        final ArrayList<Category> categories = new ArrayList<>();
        for (int i = 1; i <= 10; i++)
            categories.add(new Category());
        return categories;
    }

    public List<EventModel> getNewEvent(){
        final ArrayList<EventModel> newEvents = new ArrayList<>();
        for (int i = 1; i <= 8; i++)
            newEvents.add(new EventModel());
        return newEvents;
    }
}
