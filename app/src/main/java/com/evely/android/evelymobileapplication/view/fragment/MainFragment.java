package com.evely.android.evelymobileapplication.view.fragment;


import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.evely.android.evelymobileapplication.R;
import com.evely.android.evelymobileapplication.module.glide.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.evely.android.evelymobileapplication.view.adapter.MainFragmentAdapter;

public class MainFragment extends Fragment
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainFragment";

    private static MainFragment fragment;

    public static final int TAB_HOME = 0;
    public static final int TAB_NEARBY = 1;
    public static final int TAB_SEARCH = 2;
    public static final int TAB_FAVORITE = 3;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_navigation_container)
    TabLayout tabLayout;
    @BindView(R.id.profile_photo)
    ImageView profilePhoto;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.dummy_search_box)
    TextView actionSearch;
    @BindView(R.id.action_notifications)
    ImageView actionNotifications;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView nav;
    @Nullable
    @BindView(R.id.nav_profile_photo)
    ImageView navProfilePhoto;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.bottom_navigation_container)
    View bottomNavigation;
    @BindView(R.id.filter_actions)
    RadioGroup filterActions;
    @BindView(R.id.action_advanced_filtering)
    View actionAdvancedFiltering;
    @BindView(R.id.action_location_settings)
    View actionLocationSettings;

    private OnActionNotificationsListener onActionNotificationsListener;
    private OnActionSearchListener onActionSearchListener;

    public interface OnActionNotificationsListener {
        void onActionNotifications();
    }

    public interface OnActionSearchListener {
        void onActionSearch();
    }

    public OnActionNotificationsListener getOnActionNotificationsListener() {
        return onActionNotificationsListener;
    }

    public void setOnActionNotificationsListener(OnActionNotificationsListener onActionNotificationsListener) {
        this.onActionNotificationsListener = onActionNotificationsListener;
    }

    public OnActionSearchListener getOnActionSearchListener() {
        return onActionSearchListener;
    }

    public void setOnActionSearchListener(OnActionSearchListener onActionSearchListener) {
        this.onActionSearchListener = onActionSearchListener;
    }

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment getInstance(){
        if(fragment == null) fragment = new MainFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View content = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, content);

        //NavigationDrawer's header
        navProfilePhoto = nav.getHeaderView(0)
                .findViewById(R.id.nav_profile_photo);

        //Delegate an ActionBar
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        //Add a NavigationDrawer
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        nav.setNavigationItemSelectedListener(this);

        //Loading an image and set it as a Button to open the drawer.
        final String url = "http://160.16.140.145:8080/uploads/attachment/5a112b8294f8050056743e41/1bc055e9057ba267d43c272c55f8cd65.jpg";
        GlideApp.with(this)
                .load(url)
                .fitCenter()
                .into(profilePhoto);
        GlideApp.with(this)
                .load(url)
                .fitCenter()
                .into(navProfilePhoto);

        profilePhoto.setOnClickListener(v -> {
            if (!drawer.isDrawerOpen(Gravity.START))
                drawer.openDrawer(Gravity.START, true);
        });

        //Bind the tab navigation
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                final int tabPos = tab.getPosition();
                switch (tabPos) {
                    case TAB_HOME: {
                        toolbarTitle.setText(R.string.tab_title_home);
                        break;
                    }
                    case TAB_NEARBY: {
                        toolbarTitle.setText(R.string.tab_title_nearby);
                        break;
                    }
                    case TAB_FAVORITE: {
                        toolbarTitle.setText(R.string.tab_title_favorite);
                        break;
                    }
                }

                if (tabPos == TAB_SEARCH)
                    actionSearch.setVisibility(View.VISIBLE);
                else
                    toolbarTitle.setVisibility(View.VISIBLE);

                if (tabPos == TAB_NEARBY)
                    toggleBottomNavigation(true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                final int tabPos = tab.getPosition();
                if (tabPos == TAB_SEARCH)
                    actionSearch.setVisibility(View.GONE);
                else
                    toolbarTitle.setVisibility(View.GONE);

                if (tabPos == TAB_NEARBY)
                    toggleBottomNavigation(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        //Connect ViewPager to TabLayout
        final MainFragmentAdapter adaptor = new MainFragmentAdapter(getContext(), getChildFragmentManager());
        viewPager.setAdapter(adaptor);
        tabLayout.setupWithViewPager(viewPager);

        //Because the ViewPager overwrites tab item layouts, we need rebind them.
        final int[] tabItemLayouts = {
                R.layout.tab_item_home
                , R.layout.tab_item_nearby
                , R.layout.tab_item_search
                , R.layout.tab_item_pin
        };
        for (int position = 0; position < tabItemLayouts.length; position++)
            tabLayout.getTabAt(position)
                    .setCustomView(tabItemLayouts[position]);

        return content;
    }

    private void toggleBottomNavigation(boolean open) {
        final Rect rectangle = new Rect();
        getActivity()
                .getWindow()
                .getDecorView()
                .getWindowVisibleDisplayFrame(rectangle);
        final float screenBottom = rectangle.height();

        final float from;
        final float delta = bottomNavigation.getHeight();
        final float to;
        final ValueAnimator valueAnimator = new ValueAnimator();
        final DenormalizeInterpolator denormalizeInterpolator = new DenormalizeInterpolator();
        if (open) {
            from = screenBottom;
            to = from - delta;
            valueAnimator.setInterpolator(new DecelerateInterpolator());
        } else {
            from = screenBottom + delta;
            to = from + delta;
            valueAnimator.setInterpolator(new AccelerateInterpolator());
        }

        valueAnimator.addUpdateListener(animation -> {
            final float v = animation.getAnimatedFraction();
            final float newY = denormalizeInterpolator.interpolate(v, from, to);
            bottomNavigation.setY(newY);
        });
        valueAnimator.setFloatValues(0, 1);
        valueAnimator.setDuration(225);
        valueAnimator.start();
    }

    @OnClick(R.id.action_notifications)
    void onActionNotifications(){
        if(onActionNotificationsListener != null)
            onActionNotificationsListener.onActionNotifications();
    }

    @OnClick(R.id.dummy_search_box)
    void onActionSearch(){
        if(onActionSearchListener != null)
            onActionSearchListener.onActionSearch();
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }

    class DenormalizeInterpolator {
        public float interpolate(float fraction, float from, float to) {
            return (to - from) * fraction + from;
        }
    }
}
