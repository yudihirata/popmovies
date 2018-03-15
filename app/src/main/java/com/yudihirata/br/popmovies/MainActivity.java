package com.yudihirata.br.popmovies;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.yudihirata.br.popmovies.adapters.RestMoviesAdapter;
import com.yudihirata.br.popmovies.adapters.listener.RestAdapterListener;
import com.yudihirata.br.popmovies.data.Facade;

import org.parceler.Parcels;

import java.util.List;

import icepick.Icepick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import models.Movie;
import models.MovieResponse;
import presenter.MovieDBManager;
import presenter.Observer;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends UiMainActivity implements RestAdapterListener

{
    private Observable<List<Movie>> mObservable;  /* Observable that retrieves a list of movies*/
    private Observer mMovieObserver               /* Object to be  notified*/;
    private MovieDBManager mMovieDBManager;       /* Manager to listener the MovieDB rest api and
                                                     execute the callback*/

    private List<Movie> mList;                       /*List of movies*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovieObserver = new Observer(mRecyclerView, new RestMoviesAdapter(this));
        mMovieDBManager = new MovieDBManager();

        mObservable = Observable.create(new ObservableOnSubscribe<List<Movie>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Movie>> e) throws Exception {
                e.onNext(mList);
                e.onComplete();
            }
        });
        if (savedInstanceState == null) {
            onRefresh();
        } else {
            Icepick.restoreInstanceState(this, savedInstanceState);
        }
    }

    @Override
    public void onResponse(@android.support.annotation.NonNull Call<MovieResponse> call,
                           @android.support.annotation.NonNull Response<MovieResponse> response) {
        Log.d(Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getFileName(),
                Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getMethodName());
        if (response.isSuccessful()) {
            MovieResponse movieResponse = response.body();
            if (movieResponse != null) {
                mList = movieResponse.getResults();
                mObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mMovieObserver);
            }
        }
        super.onResponse(call, response);
    }

    @Override
    public void onFailure(@android.support.annotation.NonNull Call<MovieResponse> call,
                          @android.support.annotation.NonNull Throwable t) {
        Log.d(Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getFileName(),
                Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getMethodName());
        t.printStackTrace();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        String filter = getSelectedFilter();
        if (filter.equals(MovieDBManager.FAVORITES)) {
            UpdateFavorites();
        } else {
            mMovieDBManager.get(filter, this);
        }
    }

    private void UpdateFavorites() {
        Facade facade = new Facade(getApplicationContext());
        mMovieObserver.setList(facade.getListFavorites());
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        item.setChecked(true);
        onRefresh();
        return true;
    }

    @Override
    public void onItemClick(Movie movie) {
        Log.d(Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getFileName(),
                Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getMethodName());

        Intent detailsActivity = new Intent(MainActivity.this, DetailsActivity.class);
        detailsActivity.putExtra("Movie", Parcels.wrap(movie));
        startActivity(detailsActivity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getSelectedFilter().equals(MovieDBManager.FAVORITES)) {
            UpdateFavorites();
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getFileName(),
                Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getMethodName());
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey("mList")) {
            mList = Parcels.unwrap(savedInstanceState.getParcelable("mList"));
            mMovieObserver.setList(mList);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("mList", Parcels.wrap(mList));
    }
}



