package com.evely.android.evelymobileapplication.view.design;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import com.evely.android.evelymobileapplication.R;

import static android.view.MotionEvent.*;

/**
 * Project EvelyMobileAppplication
 * Working on PressedEffectTextView
 * Created by Shion T. Fujie on 2017/12/04.
 */
public class PressEffectWrapper extends RelativeLayout {
    private static final String TAG = "PressEffectWrapper";

    private View animationTarget;

    private Drawable revealedBackground;

    private Animator restoreAnimator;
    private AnimatorSet revealAnimator;

    @SuppressWarnings("deprecation")
    final OnTouchListener onTouchListener = (v, event) -> {
        switch (event.getActionMasked()) {
            case ACTION_DOWN: {

                final int x = (int) event.getX();
                final int y = (int) event.getY();

                final float finalRadius = Math.max(getWidth(), getHeight()) * 1.5f;

                if (revealAnimator != null && revealAnimator.isRunning()) revealAnimator.cancel();
                if (restoreAnimator != null && restoreAnimator.isRunning()) revealAnimator.cancel();

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    final Animator rawAnimator = ViewAnimationUtils.createCircularReveal(animationTarget, x, y, 0, finalRadius);
                    rawAnimator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                animationTarget.setBackground(revealedBackground);
                            } else {
                                animationTarget.setBackgroundDrawable(revealedBackground);
                            }
                        }
                    });
                    rawAnimator.setInterpolator(new DecelerateInterpolator());

                    revealAnimator = new AnimatorSet();
                    revealAnimator.play(rawAnimator);
                    revealAnimator.setStartDelay(175);
                    revealAnimator.setDuration(430);
                    revealAnimator.start();
                }
                break;
            }
            case ACTION_CANCEL:
            case ACTION_UP: {

                restoreAnimator = ObjectAnimator.ofFloat(animationTarget, "alpha", 1, 0);
                restoreAnimator.setDuration(120);
                restoreAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            animationTarget.setBackground(null);
                        } else {
                            animationTarget.setBackgroundDrawable(null);
                        }
                        animationTarget.setAlpha(1);

                        if (revealAnimator != null) {
                            revealAnimator.cancel();
                            revealAnimator = null;
                        }
                    }
                });
                restoreAnimator.start();
                break;
            }
        }

        return false;
    };

    public PressEffectWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);

        final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PressEffectWrapper);
        revealedBackground = ta.getDrawable(R.styleable.PressEffectWrapper_revealedDrawable);
        ta.recycle();

        if (revealedBackground == null) {
            final int pressedColor = ContextCompat.getColor(getContext(), R.color.colorButtonTextPressed);
            revealedBackground = new ColorDrawable(pressedColor);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (animationTarget == null) {
            Log.d(TAG, "w=" + w + " h=" + h);
            animationTarget = new RelativeLayout(getContext());
            final LayoutParams layoutParams = new LayoutParams(w, h);

            addView(animationTarget, layoutParams);

            final View touchEventSender = getChildAt(0);
            touchEventSender.setOnTouchListener(onTouchListener);
        }
    }
}
