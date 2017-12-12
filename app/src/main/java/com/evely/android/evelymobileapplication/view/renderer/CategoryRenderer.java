package com.evely.android.evelymobileapplication.view.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.evely.android.evelymobileapplication.R;
import com.evely.android.evelymobileapplication.model.Category;
import com.pedrogomez.renderers.Renderer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Shion T. Fujie.
 * Project name: EvelyMobile
 * Date : 14 / 12 / 2017
 * Using : Android Studio
 */

public class CategoryRenderer extends Renderer<Category> {
    @BindView(R.id.image_category)
    ImageView imageCategory;
    @BindView(R.id.title_category)
    TextView title;
    @BindView(R.id.sub_title_category)
    TextView subheading;

    @Override
    protected void setUpView(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void hookListeners(View rootView) {

    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.list_item_category, parent, false);
    }

    @Override
    public void render() {
        final Category category = getContent();

        title.setText(category.getTitle());
        subheading.setText(category.getSubheading());
    }
}
