package com.evely.android.evelymobileapplication.view.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.evely.android.evelymobileapplication.R;
import com.evely.android.evelymobileapplication.model.EventModel;
import com.pedrogomez.renderers.Renderer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Shion T. Fujie.
 * Project name: EvelyMobile
 * Date : 14 / 12 / 2017
 * Using : Android Studio
 */

public class NewEventRenderer extends Renderer<EventModel> {
    @BindView(R.id.image_new_event)
    ImageView imageNewEvent;
    @BindView(R.id.host_name)
    TextView hostName;
    @BindView(R.id.title_new_event)
    TextView title;
    @BindView(R.id.summary)
    TextView summary;

    @Override
    protected void setUpView(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void hookListeners(View rootView) {

    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.list_item_new_event, parent, false);
    }

    @Override
    public void render() {
        final EventModel event = getContent();

//        hostName.setText(event.getHost().getName());
//        title.setText(event.getTitle());
//        summary.setText(event.getBody());
    }
}
