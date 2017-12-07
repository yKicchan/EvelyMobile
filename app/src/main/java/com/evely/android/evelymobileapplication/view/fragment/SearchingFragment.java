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
public class SearchingFragment extends Fragment {

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
        return content;
    }

}
