package com.evely.android.evelymobileapplication.model.provider;

import android.content.SearchRecentSuggestionsProvider;

/**
 * Project EvelyMobileApplication
 * Working on RecentRecentKeywordsProvider
 * Created by Shion T. Fujie on 2017/12/10.
 */
public class RecentRecentKeywordsProvider extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "com.evely.android.model.content.provider.RecentRecentKeywordsProvider";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public RecentRecentKeywordsProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}
