package com.evely.android.evelymobileapplication.view.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.evely.android.evelymobileapplication.R;

import static com.evely.android.evelymobileapplication.HomeActivity.*;

/**
 * Created by Isom on 2017/11/17.
 */
public class HomeFragmentAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public HomeFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case TAB_HOME: {
                return TabHomeFragment.getInstance();
            }
            case TAB_NEARBY: {
                return TabNearbyFragment.getInstance();
            }
            case TAB_SEARCH: {
                return TabSearchFragment.getInstance();
            }
            case TAB_FAVORITE: {
                return TabFavoriteFragment.getInstance();
            }
        }
        return null;
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 4;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case TAB_HOME:
                return mContext.getString(R.string.tab_title_home);
            case TAB_NEARBY:
                return mContext.getString(R.string.tab_title_nearby);
            case TAB_SEARCH:
                return mContext.getString(R.string.tab_title_search);
            case TAB_FAVORITE:
                return mContext.getString(R.string.tab_title_favorite);
            default:
                return null;
        }
    }
}
