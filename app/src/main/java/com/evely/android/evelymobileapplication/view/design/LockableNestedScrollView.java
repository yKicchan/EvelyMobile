package com.evely.android.evelymobileapplication.view.design;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Shion T. Fujie.
 * Project name: EvelyMobile
 * Date : 15 / 12 / 2017
 * Using : Android Studio
 */

public class LockableNestedScrollView extends NestedScrollView {
    private static final String TAG = "LNestedScrollView";

    private boolean isScrollable = true;

    public LockableNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollable(boolean scrollable){
        isScrollable = scrollable;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        final boolean b = isScrollable && super.onTouchEvent(ev);
        Log.d(TAG, "onTouchEvent b=" + b);
        return b;
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                if (isScrollable)
//                    return super.onTouchEvent(ev);
//            default:
//                return super.onTouchEvent(ev);
//        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final boolean b  = isScrollable && super.onInterceptTouchEvent(ev);
        Log.d(TAG, "onInterceptTouchEvent b=" + b);
        return b;
//        if (! isScrollable) return false;
//        else return super.onInterceptTouchEvent(ev);
    }
}
