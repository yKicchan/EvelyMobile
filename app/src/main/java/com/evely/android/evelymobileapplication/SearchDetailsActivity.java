package com.evely.android.evelymobileapplication;

import android.app.SearchManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.evely.android.evelymobileapplication.model.EventModel;
import com.evely.android.evelymobileapplication.model.provider.RecentRecentKeywordsProvider;
import com.evely.android.evelymobileapplication.view.renderer.EventModelRenderer;
import com.evely.android.evelymobileapplication.viewmodel.LiveEventsViewModel;
import com.evely.android.utils.Units;
import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.evely.android.evelymobileapplication.EventDetailsActivity.ARGS_EVENT;

public class SearchDetailsActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.dummy_search_box)
    TextView searchText;

    @BindView(R.id.search_results)
    RecyclerView searchResults;

    private LiveEventsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_details);
        ButterKnife.bind(this);

        viewModel = ViewModelProviders.of(this).get(LiveEventsViewModel.class);
        viewModel.getLiveEvents().observe(this, events -> updateRecyclerView(createAdapter(events)));

        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final int verticalSpaceHeight = Units.dpToPx(this, 16);
        searchResults.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1)
                    outRect.bottom = verticalSpaceHeight;
            }
        });
        searchResults.setLayoutManager(new LinearLayoutManager(this));

        final Intent intent = getIntent();
        if (intent.getAction().equals(Intent.ACTION_SEARCH)) {
            final String query = intent.getStringExtra(SearchManager.QUERY);
            searchText.setText(query);

            final SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                    RecentRecentKeywordsProvider.AUTHORITY, RecentRecentKeywordsProvider.MODE);
            suggestions.saveRecentQuery(query, null);

            viewModel.getLiveEvents().requestQuery(query, null);
        }
    }

    @NonNull
    private RVRendererAdapter<EventModel> createAdapter(List<EventModel> events) {
        final EventModelRenderer renderer = new EventModelRenderer();
        renderer.setOnBookmarkedChangedListener((v, checked) ->
                Toast.makeText(this, checked ? "Bookmarked" : "Un-bookmarked", Toast.LENGTH_SHORT).show());
        renderer.setOnActionExploreListener(event -> {
            final Intent intent = new Intent(this, EventDetailsActivity.class);
            intent.putExtra(ARGS_EVENT, Parcels.wrap(event));
            startActivity(intent);
        });
        final RendererBuilder<EventModel> builder = new RendererBuilder<EventModel>()
                .bind(EventModel.class, renderer);
        final ListAdapteeCollection<EventModel> collection = new ListAdapteeCollection<>(events);
        return new RVRendererAdapter<>(builder, collection);
    }

    private void updateRecyclerView(RVRendererAdapter<EventModel> adapter) {
        if (searchResults.getAdapter() != null) {
            searchResults.swapAdapter(adapter, true);
        } else {
            searchResults.setAdapter(adapter);
        }
    }
}
