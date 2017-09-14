package com.yudihirata.br.popmovie;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.yudihirata.br.popmovie.adapters.RestAdapterListener;

import org.parceler.Parcels;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import models.Movie;
import models.MovieDBResponse;
import presenter.MovieDBManager;
import presenter.MovieDBObserver;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends UiMainActivity implements RestAdapterListener

{
    private Observable<List<Movie>> mObservable;  /* Observable that retrieves a list of movies*/
    private MovieDBObserver mMovieDBObserver      /* Object to be  notified*/;
    private MovieDBManager mMovieDBManager;       /* Manager to listener the MovieDB rest api and
                                                     execute the callback*/

    private List<Movie> mList;                       /*List of movies*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMovieDBObserver       = new MovieDBObserver(this, mRecyclerView);
        mMovieDBManager        = new MovieDBManager();

        mObservable = Observable.create(new ObservableOnSubscribe<List<Movie>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Movie>> e) throws Exception {
                e.onNext(mList);
                e.onComplete();
            }
        });
        onRefresh();
    }

    @Override
    public void onResponse(@android.support.annotation.NonNull Call<MovieDBResponse> call,
                           @android.support.annotation.NonNull Response<MovieDBResponse> response) {
        if (response.body() != null ){
            mList = response.body().getResults();
            mObservable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(mMovieDBObserver);

        }
        super.onResponse(call, response);
    }

    @Override
    public void onFailure(@android.support.annotation.NonNull Call<MovieDBResponse> call,
                          @android.support.annotation.NonNull Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        String filter = getSelectedFilter();
        mMovieDBManager.get(filter, this);
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
        Intent detailsActivity = new Intent(MainActivity.this, DetailsActivity.class );
        detailsActivity.putExtra("Movie", Parcels.wrap(movie));
        startActivity(detailsActivity);
    }
}



