package com.evely.android.evelymobileapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.evely.android.evelymobileapplication.view.fragment.MainFragment;
import com.evely.android.evelymobileapplication.view.fragment.NotificationsFragment;
import com.evely.android.evelymobileapplication.view.fragment.SearchingFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG_MAIN = "main";
    private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_SEARCH = "searching";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MainFragment fragment = MainFragment.getInstance();
        fragment.setOnActionNotificationsListener(() ->
                replaceFragmentBy(NotificationsFragment.getInstance(), TAG_NOTIFICATIONS));
        fragment.setOnActionSearchListener(() -> {
            replaceFragmentBy(SearchingFragment.getInstance(), TAG_SEARCH);
        });

        replaceFragmentBy(fragment, TAG_MAIN);
    }

    private void replaceFragmentBy(Fragment fragment, String key){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment, key)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
