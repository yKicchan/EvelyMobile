package com.evely.android.evelymobileapplication.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evely.android.evelymobileapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsFragment extends Fragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

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
        ButterKnife.bind(this, content);

        final AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        final ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        return content;
    }


}
