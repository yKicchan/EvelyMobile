package com.evely.android.evelymobileapplication.view.fragment;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;
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

    @BindView(R.id.constraint)
    ConstraintLayout constraintLayout;
    @BindView(R.id.map_view)
    MapView mapView;
    private GoogleMap map;

    private ConstraintSet collapsedCS;
    private ConstraintSet expandedCS;
    private boolean mapExpanded = false;
    private OnMapExpansionToggleListener onMapExpansionToggleListener;

    public interface OnMapExpansionToggleListener{
        void onMapExpansionToggle(GoogleMap map, boolean expanded);
    }

    public OnMapExpansionToggleListener getOnMapExpansionToggleListener() {
        return onMapExpansionToggleListener;
    }

    public void setOnMapExpansionToggleListener(OnMapExpansionToggleListener onMapExpansionToggleListener) {
        this.onMapExpansionToggleListener = onMapExpansionToggleListener;
    }

    public TabNearbyFragment() {
        // Required empty public constructor
    }

    public static TabNearbyFragment getInstance() {
        return new TabNearbyFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View content = inflater.inflate(R.layout.fragment_tab_nearby, container, false);
        ButterKnife.bind(this, content);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        collapsedCS = new ConstraintSet();
        expandedCS = new ConstraintSet();
        collapsedCS.clone(constraintLayout);
        expandedCS.clone(getContext(), R.layout.tab_neaby_map_expanded);

        return content;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        //UI settings.
        final UiSettings settings = map.getUiSettings();
        settings.setMapToolbarEnabled(false);
        settings.setAllGesturesEnabled(false);

        map.setOnMapClickListener(latLng -> {
            TransitionManager.beginDelayedTransition(constraintLayout);
            if(mapExpanded){
                collapsedCS.applyTo(constraintLayout);
            }else{
                expandedCS.applyTo(constraintLayout);
            }
            mapExpanded = ! mapExpanded;

            if(onMapExpansionToggleListener != null)
                onMapExpansionToggleListener.onMapExpansionToggle(map, mapExpanded);
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
