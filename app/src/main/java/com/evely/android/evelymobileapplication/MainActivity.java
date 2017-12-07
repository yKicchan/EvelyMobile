package com.evely.android.evelymobileapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.evely.android.evelymobileapplication.view.fragment.MainFragment;
import com.evely.android.evelymobileapplication.view.fragment.NotificationsFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG_MAIN = "main";
    private static final String TAG_NOTIFICATIONS = "notifications";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MainFragment fragment = MainFragment.getInstance();
        fragment.setOnActionNotificationsListener(() -> {
            replaceFragmentBy(NotificationsFragment.getInstance(), "Notifications");
        });

        replaceFragmentBy(fragment, "Main");
    }

    private void replaceFragmentBy(Fragment fragment, String key){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment, key)
                .addToBackStack(null)
                .commit();
    }
}
