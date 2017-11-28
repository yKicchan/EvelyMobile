package com.evely.android.evelymobileapplication.view.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.evely.android.evelymobileapplication.EventDetailsActivity;
import com.evely.android.evelymobileapplication.R;
import com.evely.android.evelymobileapplication.model.EventModel;
import com.evely.android.evelymobileapplication.view.renderer.EventModelRenderer;
import com.evely.android.evelymobileapplication.viewmodel.HomeViewModel;
import com.evely.android.utils.Units;
import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;
import org.parceler.Parcels;

import java.util.List;


public class TabHomeFragment extends Fragment {
    private static TabHomeFragment fragment;

    @BindView(R.id.event_list)
    RecyclerView eventList;

    private HomeViewModel model = new HomeViewModel();

    public TabHomeFragment() {
        // Required empty public constructor
    }

    public static TabHomeFragment getInstance() {
        if (fragment == null)
            fragment = new TabHomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View content = inflater.inflate(R.layout.fragment_tab_content_home, container, false);
        ButterKnife.bind(this, content);

        //Bind RecyclerView
        eventList.setLayoutManager(new LinearLayoutManager(getActivity()));
        final int verticalSpaceHeight = Units.dpToPx(getActivity(), 16);
        eventList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
                    outRect.bottom = verticalSpaceHeight;
                }
            }
        });
        model.getRetrievedEvents().observe(evs -> updateRecyclerView(createAdapter(evs)));
        model.getRetrievedEvents().requestRetrieval();
        return content;
    }

    @NonNull
    private RVRendererAdapter<EventModel> createAdapter(List<EventModel> events) {
        final EventModelRenderer renderer = new EventModelRenderer();
        renderer.setOnBookmarkedChangedListener((v, checked) -> {
            // TODO Replace it by the real logic.
            Toast.makeText(getActivity(), checked ? "Bookmarked" : "Un-bookmarked", Toast.LENGTH_SHORT).show();
        });
        renderer.setOnActionExploreListener(e -> {
            final Intent intent = new Intent(getContext(), EventDetailsActivity.class);
            intent.putExtra("event", Parcels.wrap(e));
            startActivity(intent);
        });
        final RendererBuilder<EventModel> builder = new RendererBuilder<EventModel>()
                .bind(EventModel.class, renderer);
        final ListAdapteeCollection<EventModel> collection = new ListAdapteeCollection<>(events);
        return new RVRendererAdapter<>(builder, collection);
    }

    private void updateRecyclerView(RVRendererAdapter<EventModel> adapter) {
        if (eventList.getAdapter() != null) {
            eventList.swapAdapter(adapter, true);
        } else {
            eventList.setAdapter(adapter);
        }
    }
}
