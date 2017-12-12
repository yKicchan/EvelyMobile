package com.evely.android.evelymobileapplication.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evely.android.evelymobileapplication.R;
import com.evely.android.evelymobileapplication.model.Category;
import com.evely.android.evelymobileapplication.model.EventModel;
import com.evely.android.evelymobileapplication.view.renderer.CategoryRenderer;
import com.evely.android.evelymobileapplication.view.renderer.NewEventRenderer;
import com.evely.android.evelymobileapplication.viewmodel.TabSearchFragmentViewModel;
import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabSearchFragment extends Fragment {
    @BindView(R.id.new_events)
    RecyclerView newEvents;
    @BindView(R.id.categories)
    RecyclerView categories;

    private TabSearchFragmentViewModel viewModel;

    public TabSearchFragment() {
        // Required empty public constructor
    }

    public static TabSearchFragment getInstance() {
        return new TabSearchFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(TabSearchFragmentViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View content = inflater.inflate(R.layout.fragment_tab_search, container, false);;
        ButterKnife.bind(this, content);

        newEvents.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        categories.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        updateRecyclerViewForNewEvents(
                createAdapterForNewEvents(viewModel.getNewEvent()));
        updateRecyclerViewForCategories(
                createAdapterForCategories(viewModel.getCategories()));

        return content;
    }

    @NonNull
    private RVRendererAdapter<EventModel> createAdapterForNewEvents(List<EventModel> events) {
        final NewEventRenderer renderer = new NewEventRenderer();
        final RendererBuilder<EventModel> builder = new RendererBuilder<EventModel>()
                .bind(EventModel.class, renderer);
        final ListAdapteeCollection<EventModel> collection = new ListAdapteeCollection<>(events);
        return new RVRendererAdapter<>(builder, collection);
    }

    private void updateRecyclerViewForNewEvents(RVRendererAdapter<EventModel> adapter) {
        if (newEvents.getAdapter() != null) {
            newEvents.swapAdapter(adapter, true);
        } else {
            newEvents.setAdapter(adapter);
        }
    }

    @NonNull
    private RVRendererAdapter<Category> createAdapterForCategories(List<Category> categories) {
        final CategoryRenderer renderer = new CategoryRenderer();
        final RendererBuilder<Category> builder = new RendererBuilder<Category>()
                .bind(Category.class, renderer);
        final ListAdapteeCollection<Category> collection = new ListAdapteeCollection<>(categories);
        return new RVRendererAdapter<>(builder, collection);
    }

    private void updateRecyclerViewForCategories(RVRendererAdapter<Category> adapter) {
        if (categories.getAdapter() != null) {
            categories.swapAdapter(adapter, true);
        } else {
            categories.setAdapter(adapter);
        }
    }
}
