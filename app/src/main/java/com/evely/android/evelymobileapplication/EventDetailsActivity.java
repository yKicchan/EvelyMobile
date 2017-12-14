package com.evely.android.evelymobileapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.evely.android.evelymobileapplication.model.EventModel;
import com.evely.android.evelymobileapplication.view.EventReviewPopupWindow;
import com.google.android.gms.maps.MapView;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class EventDetailsActivity extends AppCompatActivity{
    private static final String TAG = "EDetailsActivity";

    public static final String ARGS_EVENT = "user_id";

    @BindView(R.id.root)
    View root;
    @BindView(R.id.app_bar_image)
    ImageView topImage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    //Header
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.action_bookmark)
    View actionBookmark;
    @BindView(R.id.profile_photo)
    ImageView profilePhoto;
    @BindView(R.id.host_name)
    TextView hostName;

    //Detailed Info.
    @BindView(R.id.upcoming_date)
    TextView upcomingDate;
    @BindView(R.id.other_upcoming_dates)
    TextView otherUpcomingDates;
    @BindView(R.id.location)
    TextView locationName;
    @BindView(R.id.location_address)
    TextView locationAddress;
    @BindView(R.id.url)
    TextView webUrl;
    @BindView(R.id.tel_number)
    TextView telNumber;

    //Body
    @BindView(R.id.body_title)
    TextView bodyTitle;
    @BindView(R.id.body_rich_media)
    ImageView bodyRichMedia;
    @BindView(R.id.body_text)
    TextView bodyText;

    //Upcoming Dates
    @BindView(R.id.upcoming_dates_container)
    LinearLayout upcomingDatesContainer;
    @BindView(R.id.action_see_all_dates)
    View actionSeeAllDates;

    //Location
    @BindView(R.id.location_rich_media)
    MapView locationRichMedia;
    @BindView(R.id.location_text)
    TextView locationText;
    @BindView(R.id.action_get_directions)
    View actionGetDirections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setTitle("");

        final EventModel event = Parcels.unwrap(getIntent().getParcelableExtra(ARGS_EVENT));
        bindEvent(event);
    }

    private void bindEvent(final EventModel event){
        title.setText(event.getTitle());
        hostName.setText(event.getHost().getName());
        upcomingDate.setText(event.getUpcomingDate().getStartDate());
        locationName.setText(event.getPlace().getName());
        webUrl.setText(event.getUrl());
        telNumber.setText(event.getTel());

        bodyTitle.setText(event.getTitle());
        bodyText.setText(event.getBody());

        locationText.setText(event.getPlace().getName());
    }

    @OnClick(R.id.action_bookmark)
    void onActionBookmark(){
        final boolean isSelected = !actionBookmark.isSelected();

        actionBookmark.setSelected(isSelected);

        if(isSelected)
            showReviewWindow();
    }

    private void showReviewWindow() {
        final LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View content = inflater.inflate(R.layout.popup_window_event_review, null);
        final PopupWindow popupWindow = new EventReviewPopupWindow(content, this, MATCH_PARENT, MATCH_PARENT,true);
        popupWindow.setAnimationStyle(R.style.AnimationPopup);
        popupWindow.showAtLocation(root, Gravity.CENTER, 0, 0);
    }
}
