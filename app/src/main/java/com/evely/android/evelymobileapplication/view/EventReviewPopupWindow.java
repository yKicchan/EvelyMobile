package com.evely.android.evelymobileapplication.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;
import butterknife.*;
import com.evely.android.evelymobileapplication.EventDetailsActivity;
import com.evely.android.evelymobileapplication.R;
import com.evely.android.evelymobileapplication.model.Comment;
import com.evely.android.evelymobileapplication.model.EventModel;
import com.evely.android.evelymobileapplication.model.User;
import com.evely.android.evelymobileapplication.view.renderer.CommentRenderer;
import com.evely.android.evelymobileapplication.view.renderer.EventModelRenderer;
import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Project EvelyMobileAppplication
 * Working on EventReviewPopupWindow
 * Created by Shion T. Fujie on 2017/11/30.
 */
public class EventReviewPopupWindow extends PopupWindow {
    @BindView(R.id.action_close)
    View actionClose;
    @BindView(R.id.review_comment_list)
    RecyclerView commentList;
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

    private RVRendererAdapter<Comment> rendererAdapter;

    public EventReviewPopupWindow(View content, Activity activity, int width, int height, boolean focusable){
        super(content, width, height, focusable);
        this.activity = activity;
        ButterKnife.bind(this, content);

        actionSend.setEnabled(false);

        commentList.setLayoutManager(new LinearLayoutManager(activity));
        rendererAdapter = createAdapter(new ArrayList<>());
        updateRecyclerView(rendererAdapter);
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

    @OnClick(R.id.action_send)
    void onActionSend(){
        final String commentText = commentMessage.getText().toString();
        commentMessage.setText("");
        rendererAdapter.add(new Comment(commentText, new User()));

        final int position = rendererAdapter.getItemCount();
        rendererAdapter.notifyItemInserted(position - 1);

        final InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(commentMessage.getWindowToken(), 0);
    }

    @NonNull
    private RVRendererAdapter<Comment> createAdapter(List<Comment> comments) {
        final CommentRenderer renderer = new CommentRenderer();
        final RendererBuilder<Comment> builder = new RendererBuilder<Comment>()
                .bind(Comment.class, renderer);
        final ListAdapteeCollection<Comment> collection = new ListAdapteeCollection<>(comments);
        return new RVRendererAdapter<>(builder, collection);
    }

    private void updateRecyclerView(RVRendererAdapter<Comment> adapter) {
        if (commentList.getAdapter() != null) {
            commentList.swapAdapter(adapter, true);
        } else {
            commentList.setAdapter(adapter);
        }
    }
}
