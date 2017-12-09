package com.evely.android.evelymobileapplication.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.evely.android.evelymobileapplication.R;
import com.evely.android.evelymobileapplication.SearchDetailsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchingFragment extends Fragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_view)
    SearchView searchView;

    public SearchingFragment() {
        // Required empty public constructor
    }

    public static SearchingFragment getInstance(){
        return new SearchingFragment();
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

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                final Intent intent = new Intent(getContext(), SearchDetailsActivity.class);
                intent.putExtra("query", query);
                startActivity(intent);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return content;
    }
}
