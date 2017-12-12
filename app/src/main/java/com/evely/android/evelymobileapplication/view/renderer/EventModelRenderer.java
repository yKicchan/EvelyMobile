package com.evely.android.evelymobileapplication.view.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.evely.android.evelymobileapplication.R;
import com.evely.android.evelymobileapplication.model.EventModel;
import com.evely.android.evelymobileapplication.model.User;
import com.evely.android.evelymobileapplication.module.glide.GlideApp;
import com.pedrogomez.renderers.Renderer;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Project SampleApiConnectionApp
 * Working on EventModelRenderer
 * Created by Shion T. Fujie on 2017/11/13.
 */
public class EventModelRenderer extends Renderer<EventModel> {
    @BindView(R.id.rich_media)
    ImageView richMedia;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.action_bookmark)
    View actionBookmark;
    @BindView(R.id.host_name)
    TextView hostName;
    @BindView(R.id.profile_photo)
    CircleImageView profilePhoto;
    @BindView(R.id.body)
    TextView body;
    @BindView(R.id.upcoming_date)
    TextView upcomingDate;
    @BindView(R.id.other_upcoming_dates)
    TextView otherUpcomingDates;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.location_address)
    TextView locationAddress;
    @BindView(R.id.action_explore)
    View actionExplore;
    @BindView(R.id.action_share)
    View actionShare;

    public interface OnCheckedChangedListener {
        void onCheckedChanged(View v, boolean checked);
    }

    public interface OnActionExploreListener {
        void onActionExplore(EventModel eventModel);
    }

    private OnCheckedChangedListener onBookmarkedChangedListener;
    private OnActionExploreListener onActionExploreListener;

    @OnClick(R.id.action_bookmark)
    void onBookmarkedChange() {
        final boolean isSelected = !actionBookmark.isSelected();
        //TODO Update a model.
        actionBookmark.setSelected(isSelected);
        if (onBookmarkedChangedListener != null)
            onBookmarkedChangedListener.onCheckedChanged(actionBookmark, isSelected);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.action_explore)
    void onActionExplore(){
        if(onActionExploreListener != null)
            onActionExploreListener.onActionExplore(getContent());
    }

    @Override
    protected void setUpView(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void hookListeners(View rootView) {
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.list_item_event, parent, false);
    }

    @SuppressWarnings("unused")
    @Override
    public void render() {
        final EventModel event = getContent();
        final User host = event.getHost();

        GlideApp.with(getContext())
                .load("http://160.16.140.145:8080/uploads/attachment/5a112b8294f8050056743e41/1bc055e9057ba267d43c272c55f8cd65.jpg")
                .fitCenter()
                .into(profilePhoto);
        title.setText(event.getTitle());
        hostName.setText(host.getName());
        body.setText(event.getBody());
        upcomingDate.setText(event.getUpcomingDate().getStartDate());
        location.setText(event.getPlace().getName());
    }

    @SuppressWarnings("unused")
    public OnCheckedChangedListener getOnBookmarkedChangedListener() {
        return onBookmarkedChangedListener;
    }

    @SuppressWarnings("unused")
    public void setOnBookmarkedChangedListener(OnCheckedChangedListener onBookmarkedChangedListener) {
        this.onBookmarkedChangedListener = onBookmarkedChangedListener;
    }

    @SuppressWarnings("unused")
    public OnActionExploreListener getOnActionExploreListener() {
        return onActionExploreListener;
    }

    @SuppressWarnings("unused")
    public void setOnActionExploreListener(OnActionExploreListener onActionExploreListener) {
        this.onActionExploreListener = onActionExploreListener;
    }
}
