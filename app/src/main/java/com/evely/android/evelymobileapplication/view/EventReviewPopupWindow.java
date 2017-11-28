package com.evely.android.evelymobileapplication.view;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import butterknife.*;
import com.evely.android.evelymobileapplication.R;

/**
 * Project EvelyMobileAppplication
 * Working on EventReviewPopupWindow
 * Created by Shion T. Fujie on 2017/11/30.
 */
public class EventReviewPopupWindow extends PopupWindow {
    @BindView(R.id.action_close)
    View actionClose;
    @BindView(R.id.comment_message)
    EditText commentMessage;
    @BindView(R.id.action_capture_image)
    View actionCaptureImage;
    @BindView(R.id.action_select_gif)
    View actionSelectGif;
    @BindView(R.id.action_send)
    View actionSend;

    @BindColor(R.color.colorPrimaryDark)
    int colorPrimaryDark;

    private Activity activity;

    public EventReviewPopupWindow(View content, Activity activity, int width, int height, boolean focusable){
        super(content, width, height, focusable);
        this.activity = activity;
        ButterKnife.bind(this, content);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(Color.BLACK);
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(colorPrimaryDark);
        }
    }

    @OnClick(R.id.action_close)
    void onActionClose(){
        dismiss();
    }

    @OnTextChanged(R.id.comment_message)
    void onTextChanged(CharSequence charSequence){
        actionSend.setEnabled(charSequence.length() > 0);
    }
}
