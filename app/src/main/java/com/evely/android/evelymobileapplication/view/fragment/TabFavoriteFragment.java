package com.evely.android.evelymobileapplication.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evely.android.evelymobileapplication.R;

public class TabFavoriteFragment extends Fragment {

    public TabFavoriteFragment() {
        // Required empty public constructor
    }

    public static TabFavoriteFragment getInstance() {
        return new TabFavoriteFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_content_favorite, container, false);
    }
}
