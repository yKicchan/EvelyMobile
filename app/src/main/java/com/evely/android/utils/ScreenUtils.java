package com.evely.android.utils;

import android.app.Activity;
import android.graphics.Rect;

/**
 * Created by Shion T. Fujie.
 * Project name: EvelyMobile
 * Date : 14 / 12 / 2017
 * Using : Android Studio
 */

public class ScreenUtils {
    private ScreenUtils(){}

    public static int getScreenHeight(Activity activity){
        final Rect rectangle = new Rect();
        activity.getWindow()
                .getDecorView()
                .getWindowVisibleDisplayFrame(rectangle);
        return rectangle.height();
    }

    public static int getScreenWidth(Activity activity){
        final Rect rectangle = new Rect();
        activity.getWindow()
                .getDecorView()
                .getWindowVisibleDisplayFrame(rectangle);
        return rectangle.width();
    }
}
