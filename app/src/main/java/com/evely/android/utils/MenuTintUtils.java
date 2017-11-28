package com.evely.android.utils;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import com.evely.android.evelymobileapplication.R;

/**
 * Project SampleApiConnectionApp
 * Working on MenuTintUtils
 * Created by Shion T. Fujie on 2017/11/17.
 */

/**
 * An Utility class, for compatibility with API level lower than 26,
 * utilizes tinting icons.
 */
public class MenuTintUtils {
    private MenuTintUtils() {
    }

    public static void tintAllIcons(Menu menu, final int color) {
        for (int i = 0; i < menu.size(); ++i) {
            final MenuItem item = menu.getItem(i);
            tintMenuItemIcon(color, item);
            tintShareIconIfPresent(color, item);
        }
    }

    public static void tintMenuItemIcon(int color, MenuItem item) {
        final Drawable drawable = item.getIcon();
        if (drawable != null) {
            drawable.mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN);
            item.setIcon(drawable);
        }
    }

    public static void tintShareIconIfPresent(int color, MenuItem item) {
        if (item.getActionView() != null) {
            final View actionView = item.getActionView();
            final View expandActivitiesButton = actionView.findViewById(R.id.expand_activities_button);
            if (expandActivitiesButton != null) {
                final ImageView image = expandActivitiesButton.findViewById(R.id.image);
                if (image != null) {
                    final Drawable drawable = image.getDrawable();
                    final Drawable wrapped = DrawableCompat.wrap(drawable);
                    drawable.mutate();
                    DrawableCompat.setTint(wrapped, color);
                    image.setImageDrawable(drawable);
                }
            }
        }
    }
}
