package com.evely.android.evelymobileapplication.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evely.android.evelymobileapplication.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabNearbyFragment extends Fragment
        implements OnMapReadyCallback {
    private static final String TAG = "TabNearbyFragment";

    @BindView(R.id.map_view)
    MapView mapView;
    private GoogleMap map;

    public TabNearbyFragment() {
        // Required empty public constructor
    }

    public static TabNearbyFragment getInstance() {
        return new TabNearbyFragment();
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
        final View content = inflater.inflate(R.layout.fragment_tab_nearby, container, false);
        ButterKnife.bind(this, content);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        return content;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        //UI settings.
        final UiSettings settings = map.getUiSettings();
        settings.setMapToolbarEnabled(false);
        settings.setAllGesturesEnabled(false);

        // Add a listener so that marker will move when the map is clicked.
        map.setOnMapClickListener(latLng -> {

        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mapView != null)
            mapView.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        if (mapView != null)
            mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mapView != null)
            mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mapView != null)
            mapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mapView != null)
            mapView.onSaveInstanceState(outState);
    }
}
