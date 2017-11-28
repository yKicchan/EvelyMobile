package com.evely.android.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Project Activities
 * Created by Shion T. Fujie on 2017/08/06.
 */
public class Units {
    private static final int K = 1000;

    private Units() {
    }

    static public int dpToPx(Context context, int dp) {
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    static public int pxToDp(Context context, int px) {
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    /**
     * Converts 1000 to 1K and rounds up so that the result has at most one digit after
     * the decimal point.
     *
     * @param i an integer to be formatted
     * @return the formatted expression of i
     */
    static public String format(int i) {
        if (i < K) {
            return String.valueOf(i);
        } else {
            final StringBuilder integral = new StringBuilder().append(i / K);
            final int decimal = Math.round((i % K) * 0.001f);

            if (decimal != 0)
                integral.append(".").append(decimal);

            return integral.append("k").toString();
        }
    }
}
