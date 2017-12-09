package com.evely.android.evelymobileapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchDetailsActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.dummy_search_box)
    TextView searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_details);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final Intent intent = getIntent();
        searchText.setText(intent.getStringExtra("query"));
    }
}
