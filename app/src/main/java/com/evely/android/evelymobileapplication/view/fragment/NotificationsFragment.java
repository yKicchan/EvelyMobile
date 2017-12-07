package com.evely.android.evelymobileapplication.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evely.android.evelymobileapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsFragment extends Fragment {

    private static NotificationsFragment fragment;

    public NotificationsFragment() {
        // Required empty public constructor
    }

    public static NotificationsFragment getInstance(){
        if(fragment == null) fragment = new NotificationsFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View content = inflater.inflate(R.layout.fragment_notifications, container, false);

        return content;
    }



}
