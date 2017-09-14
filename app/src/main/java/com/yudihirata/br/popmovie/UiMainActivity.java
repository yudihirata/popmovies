package com.yudihirata.br.popmovie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import icepick.Icepick;
import icepick.State;
import models.MovieDBResponse;
import presenter.MovieDBManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by marco.hirata on 04/09/2017.
 */

public abstract class UiMainActivity extends AppCompatActivity implements Callback<MovieDBResponse>,
        SwipeRefreshLayout.OnRefreshListener{
    private static final int COLUMNS = 2;
    RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @SuppressWarnings("WeakerAccess")
    @State
    int mFilter_selected = R.id.menu_item_top_rated;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Icepick.restoreInstanceState(this, savedInstanceState);

        /*Recycler view*/
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movies);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, COLUMNS));
        mRecyclerView.setHasFixedSize(true);

        /* Swipe refresh*/
        mSwipeRefreshLayout = (SwipeRefreshLayout)
                findViewById(R.id.activity_main_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filter, menu);
        menu.setGroupCheckable(R.id.radio_group_menu, true, true);
        menu.findItem(mFilter_selected).setChecked(true);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mFilter_selected =  item.getItemId();

        return true;
    }

    @Override
    public void onResponse(@NonNull Call<MovieDBResponse> call,
                           @NonNull Response<MovieDBResponse> response){
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    String getSelectedFilter() {
        return (mFilter_selected == R.id.menu_item_popular)? MovieDBManager.POPULAR :
                MovieDBManager.TOP_RATED;
    }
}
