package com.evely.android.evelymobileapplication.view.fragment;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.evely.android.evelymobileapplication.R;
import com.evely.android.evelymobileapplication.view.adapter.RecentSuggestionsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchingFragment extends Fragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    SearchView searchView;

    @BindView(R.id.recent_searches)
    RecyclerView recentSearches;

    private AppCompatActivity activity;

    public SearchingFragment() {
        // Required empty public constructor
    }

    public static SearchingFragment getInstance(){
        return new SearchingFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View content = inflater.inflate(R.layout.fragment_searching, container, false);
        ButterKnife.bind(this, content);

        activity = (AppCompatActivity) getActivity();

        activity.setSupportActionBar(toolbar);
        final ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        recentSearches.setLayoutManager(new LinearLayoutManager(getContext()));
        final RecentSuggestionsAdapter adapter = new RecentSuggestionsAdapter(getContext());
        adapter.setOnSuggestionClickListener(query -> searchView.setQuery(query, true));
        recentSearches.setAdapter(adapter);

        return content;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_searching, menu);

        final SearchManager searchManager = (SearchManager) activity.getSystemService(Context.SEARCH_SERVICE);
        final MenuItem menuItem = menu.findItem(R.id.menu_toolbarsearch);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setIconifiedByDefault(false);
        final ImageView magIcon = searchView.findViewById(R.id.search_mag_icon);
        magIcon.setImageDrawable(null);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity.getComponentName()));
        searchView.setSuggestionsAdapter(null);

        // Hack for making the keyboard appear
        searchView.setIconified(true);
        searchView.setIconified(false);
    }
}
