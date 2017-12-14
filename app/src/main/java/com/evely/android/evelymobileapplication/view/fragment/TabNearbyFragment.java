package com.evely.android.evelymobileapplication.view.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evely.android.evelymobileapplication.R;
import com.evely.android.evelymobileapplication.view.design.LockableNestedScrollView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabNearbyFragment extends Fragment
        implements OnMapReadyCallback {
    private static final String TAG = "TabNearbyFragment";

    @BindView(R.id.nested_scroll_view)
    LockableNestedScrollView nestedScrollView;

    @BindView(R.id.constraint)
    ConstraintLayout constraintLayout;
    @BindView(R.id.map_view)
    MapView mapView;
    private GoogleMap map;

    private ConstraintSet collapsedCS;
    private ConstraintSet expandedCS;
    private boolean mapExpanded = false;
    private OnMapExpansionToggleListener onMapExpansionToggleListener;
    private DiminishingGroup diminishingGroup;

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

        diminishingGroup = DiminishingGroup.in(constraintLayout)
                .without(R.id.map_view)
                .build();

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
            mapExpanded = ! mapExpanded;

            settings.setAllGesturesEnabled(mapExpanded);

            if(mapExpanded){
                expandedCS.applyTo(constraintLayout);
            }else{
                collapsedCS.applyTo(constraintLayout);
            }

            diminishingGroup.animate(mapExpanded);

            if(onMapExpansionToggleListener != null)
                onMapExpansionToggleListener.onMapExpansionToggle(map, mapExpanded);

            final boolean scrollable = ! mapExpanded;
            nestedScrollView.setScrollable(scrollable);
        });
    }

    static class DiminishingGroup {
        private List<ObjectAnimator> animators;

        DiminishingGroup(List<ObjectAnimator> animators){
            this.animators = animators;
        }

        static Factory in(ViewGroup target){
            return new Factory(target);
        }

        void animate(boolean diminish){
            cancel();

            if(diminish)
                start();
            else
                reverse();
        }

        private void start(){
            for(Animator animator: animators)
                animator.start();
        }

        private void reverse(){
            for(ObjectAnimator animator: animators)
                animator.reverse();
        }

        private void cancel(){
            for(Animator animator: animators)
                animator.cancel();
        }

        static class Factory{
            final Set<Integer> excludedIds;
            private ViewGroup target;

            Factory(ViewGroup target){
                this.target = target;
                excludedIds = new HashSet<>();
            }

            Factory without(@IdRes int id){
                excludedIds.add(id);

                return this;
            }

            DiminishingGroup build(){
                final List<ObjectAnimator> animators = new ArrayList<>();

                for(int i = 0; i < target.getChildCount(); i ++){
                    final View view = target.getChildAt(i);

                    if( ! excludedIds.contains(view.getId()) )
                        animators.add(getDiminishingAnimator(view));
                }

                return new DiminishingGroup(animators);
            }

            private ObjectAnimator getDiminishingAnimator(View target){
                final ObjectAnimator animator = ObjectAnimator.ofFloat(target, "alpha", 1, 0);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation, boolean isReverse) {
                        Log.d(TAG, "DiminishAnimator.onAnimationStart");
                        super.onAnimationStart(animation, isReverse);
                        if( isReverse ){
                            target.setVisibility(View.VISIBLE);
                        }else {
                            target.setEnabled(false);
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animation, boolean isReverse) {
                        Log.d(TAG, "DiminishAnimator.onAnimationEnd");
                        super.onAnimationEnd(animation, isReverse);
                        if( isReverse ){
                            target.setEnabled(true);
                        }else {
                            target.setVisibility(View.GONE);
                        }
                    }
                });
                animator.setDuration(175);

                return animator;
            }
        }
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
