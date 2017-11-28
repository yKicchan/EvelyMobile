package com.evely.android.evelymobileapplication.view.design;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.evely.android.evelymobileapplication.R;
import com.evely.android.utils.Units;

/**
 * Project Activities
 * Created by Shion T. Fujie on 2017/08/15.
 */
public class Separator extends View {
    public static final int ORIENTATION_VERTICAL = 0;
    public static final int ORIENTATION_HORIZONTAL = 1;
    private static final String TAG = "Separator";
    private static final int GRAVITY_TOP = 0x30;
    private static final int GRAVITY_BOTTOM = 0x50;
    private static final int GRAVITY_LEFT = 0x03;
    private static final int GRAVITY_RIGHT = 0x05;
    private static final int GRAVITY_CENTER_VERTICAL = 0x10;
    private static final int GRAVITY_CENTER_HORIZONTAL = 0x01;
    private static final int GRAVITY_CENTER = 0x11;

    //The x coordinate of the relative origin with respect to padding in the absolute coordinate system.
    private int relativeX;
    //The y coordinate of the relative origin with respect to padding in the absolute coordinate system.
    private int relativeY;
    //The width without padding.
    private int relativeWidth;
    //The height without padding
    private int relativeHeight;

    private float strokeWidth;
    private int orientation;
    private int gravity;
    private int fillColor;

    private Paint strokePaint;
    private float startX;
    private float startY;
    private float stopX;
    private float stopY;

    public Separator(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Separator, 0, 0);
        strokeWidth = ta.getDimension(R.styleable.Separator_strokeWidth, Units.dpToPx(context, 1));
        orientation = ta.getInt(R.styleable.Separator_orientation, ORIENTATION_HORIZONTAL);
        gravity = ta.getInteger(R.styleable.Separator_layout_gravity, GRAVITY_CENTER);
        fillColor = ta.getColor(R.styleable.Separator_fill_color, Color.BLACK);
        ta.recycle();

        //Cannot be negative.
        strokeWidth = Math.max(strokeWidth, 0);

        initPaint();
    }

    private void initPaint() {
        strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        strokePaint.setColor(fillColor);
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = Math.max(strokeWidth, 0);
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        if (orientation == ORIENTATION_HORIZONTAL || orientation == ORIENTATION_VERTICAL)
            this.orientation = orientation;
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public int getFillColor() {
        return fillColor;
    }

    public void setFillColor(int fillColor) {
        this.fillColor = fillColor;

        strokePaint.setColor(fillColor);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // Account for padding
        int xpad = getPaddingLeft() + getPaddingRight();
        int ypad = getPaddingTop() + getPaddingBottom();

        //Calculate the relative rectangle.
        relativeX = getPaddingLeft();
        relativeY = getPaddingTop();
        relativeWidth = w - xpad;
        relativeHeight = h - ypad;

        calculateActualStrokeWidth();
        calculateStartAndStopPoint();
    }

    private void calculateStartAndStopPoint() {
        final float actualStrokeWidthHalf = strokePaint.getStrokeWidth() / 2;

        if (orientation == ORIENTATION_HORIZONTAL) {
            startX = relativeX;
            stopX = startX + relativeWidth;
            if ((gravity & GRAVITY_TOP) == GRAVITY_TOP) {
                startY = stopY = relativeY + actualStrokeWidthHalf;
            } else if ((gravity & GRAVITY_BOTTOM) == GRAVITY_BOTTOM) {
                startY = stopY = relativeY + relativeHeight - actualStrokeWidthHalf;
            } else {
                startY = stopY = relativeY + relativeHeight / 2;
            }
        } else {
            startY = relativeY;
            stopY = startY + relativeHeight;
            if ((gravity & GRAVITY_LEFT) == GRAVITY_LEFT) {
                startX = stopX = relativeX + actualStrokeWidthHalf;
            } else if ((gravity & GRAVITY_RIGHT) == GRAVITY_RIGHT) {
                startX = stopX = relativeX + relativeWidth - actualStrokeWidthHalf;
            } else {
                startX = stopX = relativeX + relativeWidth / 2;
            }
        }
    }

    private void calculateActualStrokeWidth() {
        final float actualStrokeWidth = Math.min(strokeWidth
                , orientation == ORIENTATION_VERTICAL ? relativeWidth : relativeHeight);
        strokePaint.setStrokeWidth(actualStrokeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawLine(startX, startY, stopX, stopY, strokePaint);
    }

    private int desiredSizeOrElseSpec(int desired, int spec) {
        int mode = MeasureSpec.getMode(spec);
        int size = MeasureSpec.getSize(spec);

        //Measure Width
        if (mode == MeasureSpec.EXACTLY) {
            //Must be this size
            return size;
        } else if (mode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            return Math.min(desired, size);
        } else {
            //Be whatever you want
            return desired;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        ViewGroup.LayoutParams lp = getLayoutParams();
        if (lp.height == ViewGroup.LayoutParams.WRAP_CONTENT && orientation == ORIENTATION_HORIZONTAL) {
            setMinimumHeight((int) strokeWidth);
        } else if (lp.width == ViewGroup.LayoutParams.WRAP_CONTENT && orientation == ORIENTATION_VERTICAL) {
            setMinimumWidth((int) strokeWidth);
        }

        int desiredWidth = getSuggestedMinimumWidth() + getPaddingLeft() + getPaddingRight();
        int desiredHeight = getSuggestedMinimumHeight() + getPaddingTop() + getPaddingBottom();

        setMeasuredDimension(desiredSizeOrElseSpec(desiredWidth, widthMeasureSpec),
                desiredSizeOrElseSpec(desiredHeight, heightMeasureSpec));
    }
}