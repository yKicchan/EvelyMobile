package com.evely.android.evelymobileapplication.view.adapter;

import android.app.SearchManager;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.evely.android.evelymobileapplication.R;
import com.evely.android.evelymobileapplication.model.provider.RecentRecentKeywordsProvider;

/**
 * Project EvelyMobileApplication
 * Working on RecentSuggestionsAdapter
 * Created by Shion T. Fujie on 2017/12/10.
 */
public class RecentSuggestionsAdapter extends RecyclerView.Adapter<RecentSuggestionsAdapter.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM  = 1;

    private final Context context;
    private final Cursor cursor;

    private OnSuggestionClickListener onSuggestionClickListener;

    public interface OnSuggestionClickListener {
        void onSuggestionClick(String suggestion);
    }

    public OnSuggestionClickListener getOnSuggestionClickListener() {
        return onSuggestionClickListener;
    }

    public void setOnSuggestionClickListener(OnSuggestionClickListener onSuggestionClickListener) {
        this.onSuggestionClickListener = onSuggestionClickListener;
    }

    public RecentSuggestionsAdapter(Context context){
        this.context = context;

        final ContentResolver contentResolver = context.getContentResolver();
        final Uri uri = Uri.parse("content://" + RecentRecentKeywordsProvider.AUTHORITY
                + "/" + SearchManager.SUGGEST_URI_PATH_QUERY);
        cursor = contentResolver.query(uri, null, null, new String[]{null}, null);
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0)
            return TYPE_HEADER;
        else
            return TYPE_ITEM;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(context);

        switch (viewType){
            case TYPE_HEADER:{
                final View header = inflater.inflate(R.layout.list_item_header_recent_searches, parent, false);
                return new ViewHeaderHolder(header);
            }
            case TYPE_ITEM:{
                final View view = LayoutInflater.from(context).inflate(R.layout.list_item_recent_search,  parent, false);
                return new ViewItemHolder(view);
            }
        }

        throw new IllegalArgumentException("Unknown viewType=" + viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(holder instanceof ViewHeaderHolder) {
            final ViewHeaderHolder headerHolder = (ViewHeaderHolder) holder;

            headerHolder.headerName.setText(R.string.header_recent_searches);
        }else if(holder instanceof ViewItemHolder) {
            final ViewItemHolder itemHolder = (ViewItemHolder) holder;

            if (cursor.moveToPosition(position - 1)) {
                final int idx = cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1);
                final String suggest = cursor.getString(idx);
                itemHolder.searchedWord.setText(suggest);
            }
        }
    }

    @Override
    public int getItemCount() {
        final int recentSearchesCount = cursor.getCount();

        if(recentSearchesCount == 0)
            return 0;
        else
            return recentSearchesCount + 1;//Plus a header.
    }

    abstract class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    class ViewHeaderHolder extends ViewHolder{
        @BindView(R.id.header_name)
        TextView headerName;

        ViewHeaderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ViewItemHolder extends ViewHolder{
        @BindView(R.id.searched_word)
        TextView searchedWord;
        @BindView(R.id.action_remove)
        View actionRemove;

        ViewItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.searched_word)
        void onSuggestionClick(){
            if(onSuggestionClickListener != null)
                onSuggestionClickListener.onSuggestionClick(searchedWord.getText().toString());
        }
    }
}
