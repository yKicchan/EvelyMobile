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
public class SearchingFragment extends Fragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private static SearchingFragment fragment;

    public SearchingFragment() {
        // Required empty public constructor
    }

    public static SearchingFragment getInstance(){
        if(fragment == null) fragment = new SearchingFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View content = inflater.inflate(R.layout.fragment_searching, container, false);
        ButterKnife.bind(this, content);

        final AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        final ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        return content;
    }

}
