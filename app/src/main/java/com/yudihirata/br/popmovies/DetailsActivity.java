package com.yudihirata.br.popmovies;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.yudihirata.br.popmovies.adapters.RestReviewsAdapter;
import com.yudihirata.br.popmovies.adapters.RestTrailersAdapter;
import com.yudihirata.br.popmovies.adapters.listener.RestAdapterTrailersListener;
import com.yudihirata.br.popmovies.data.Facade;

import org.parceler.Parcels;

import java.text.DecimalFormat;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import models.Movie;
import models.Review;
import models.ReviewsResponse;
import models.Trailer;
import models.TrailerResponse;
import presenter.MovieDBManager;
import presenter.Observer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends UiDetailsActivity implements RestAdapterTrailersListener {

    private Observable<List<Review>> mObservableReviews;  /* Observable that retrieves a reviews
                                                             of movies*/
    private Observable<List<Trailer>> mObservableTrailers;/* Observable that Trailers a reviews
                                                             of movies*/
    private MovieDBManager mMovieDBManager;        /* Manager to listener the MovieDB rest api and
                                                     execute the callback*/
    private Movie mMovie;
    private List<Review> mListReviews;             /*reviews's movies*/
    private List<Trailer> mListTrailers;            /*Trailers's movies*/
    private Observer mObserverReviews;
    private Observer mObserverTrailers;
    private Facade mFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra("Movie")) {
            mMovie = Parcels.unwrap(getIntent().getParcelableExtra("Movie"));

            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(mMovie.getTitle());
            }

            mObserverReviews = new Observer(mRecyclerViewReviews,
                    new RestReviewsAdapter());

            mObserverTrailers = new Observer(mRecyclerViewTrailers,
                    new RestTrailersAdapter(this));

            mMovieDBManager = new MovieDBManager();

            mObservableReviews = Observable.create(new ObservableOnSubscribe<List<Review>>() {
                @Override
                public void subscribe(ObservableEmitter<List<Review>> e) throws Exception {
                    e.onNext(mListReviews);
                    e.onComplete();
                }
            });
            mFacade = new Facade(this);
            mFloatingActionButton.setColorFilter(mFacade.isFavorite(mMovie) ? Color.RED : Color.BLACK);
            mObservableTrailers = Observable.create(new ObservableOnSubscribe<List<Trailer>>() {
                @Override
                public void subscribe(ObservableEmitter<List<Trailer>> e) throws Exception {
                    e.onNext(mListTrailers);
                    e.onComplete();
                }
            });

            mTitle.setText(mMovie.getOriginalTitle());
            mSynopsis.setText(mMovie.getOverview());
            mReleaseDate.setText(mMovie.getReleaseDate("MMM dd, yyyy"));
            mVoteCount.setText(String.valueOf(mMovie.getVoteCount()));
            mRatingBar.setRating((float) mMovie.getVoteAverage() / 10);
            DecimalFormat df = new DecimalFormat("##.0");
            mRate.setText(df.format(mMovie.getVoteAverage()));
            mToolbar.setTitle(mMovie.getTitle());

            Picasso.with(this)
                    .load(mMovie.getBackdropUri("w500").toString())
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.poster_default)
                    .error(R.drawable.poster_default)
                    .into(mBackdrop);

            Picasso.with(this)
                    .load(mMovie.getPosterUri("w500").toString())
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.poster_default)
                    .error(R.drawable.poster_default)
                    .into(mPoster);
            onRefresh();
        }

    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mMovieDBManager.getReviews(String.valueOf(mMovie.getId()), new Callback<ReviewsResponse>() {
            @Override
            public void onResponse(@NonNull Call<ReviewsResponse> call,
                                   @NonNull Response<ReviewsResponse> response) {
                if (response.isSuccessful()) {
                    ReviewsResponse reviewsResponse = response.body();
                    if (reviewsResponse != null) {
                        mListReviews = reviewsResponse.getResults();
                        mObservableReviews
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(mObserverReviews);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ReviewsResponse> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });

        mMovieDBManager.getTrailers(String.valueOf(mMovie.getId()), new Callback<TrailerResponse>() {
            @Override
            public void onResponse(@NonNull Call<TrailerResponse> call,
                                   @NonNull Response<TrailerResponse> response) {
                if (response.isSuccessful()) {
                    TrailerResponse trailerResponse = response.body();
                    if (trailerResponse != null) {
                        mListTrailers = trailerResponse.getResults();
                        mObservableTrailers
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(mObserverTrailers);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<TrailerResponse> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onTrailerClick(Trailer trailer) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" +
                trailer.getKey()));
        Intent webIntent = new Intent(Intent.ACTION_VIEW, trailer.getYoutubeTrailer());
        try {
            getApplicationContext().startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            getApplicationContext().startActivity(webIntent);
        }
    }

    @Override
    public void onClickFloatingActionButton(View view) {
        if (mFacade.isFavorite(mMovie)) {
            if (mFacade.removeFavorite(mMovie)) {
                mFloatingActionButton.setColorFilter(Color.BLACK);
            }
        } else {
            if (mFacade.setFavorite(mMovie)) {
                mFloatingActionButton.setColorFilter(Color.RED);
            }
        }
    }
}
