package com.yudihirata.br.popmovies;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import icepick.Icepick;
import icepick.State;
import models.MovieResponse;
import presenter.MovieDBManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by marco.hirata on 04/09/2017.
 */

public abstract class UiMainActivity extends AppCompatActivity implements Callback<MovieResponse>,
        SwipeRefreshLayout.OnRefreshListener {

    static final int STACK_TRACE_LEVELS_UP = 2;
    private static final int COLUMNS = 2;
    RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    @SuppressWarnings("WeakerAccess")
    @State
    int mFilter_selected = R.id.menu_item_top_rated;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getFileName(),
                Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getMethodName());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Icepick.restoreInstanceState(this, savedInstanceState);

        /*Recycler view*/
        mRecyclerView = findViewById(R.id.rv_movies);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, COLUMNS));
        }


        mRecyclerView.setHasFixedSize(true);

        /* Swipe refresh*/
        mSwipeRefreshLayout = findViewById(R.id.activity_main_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getFileName(),
                Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getMethodName());
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filter, menu);
        menu.setGroupCheckable(R.id.radio_group_menu, true, true);
        menu.findItem(mFilter_selected).setChecked(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onRefresh() {
        Log.d(Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getFileName(),
                Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getMethodName());
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getFileName(),
                Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getMethodName());
        mFilter_selected = item.getItemId();

        return true;
    }

    @Override
    public void onResponse(@NonNull Call<MovieResponse> call,
                           @NonNull Response<MovieResponse> response) {
        Log.d(Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getFileName(),
                Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getMethodName());
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getFileName(),
                Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getMethodName());
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    String getSelectedFilter() {
        switch (mFilter_selected) {
            case R.id.menu_item_popular:
                return MovieDBManager.POPULAR;
            case R.id.menu_item_top_rated:
                return MovieDBManager.TOP_RATED;
            default:
                return MovieDBManager.FAVORITES;
        }
    }
}
