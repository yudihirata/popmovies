package com.yudihirata.br.popmovie;

import android.os.Bundle;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import models.Movie;

public class DetailsActivity extends UiDetailsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ( getIntent().hasExtra("Movie") ){
            Movie movie  = Parcels.unwrap(getIntent().getParcelableExtra("Movie"));
            if (getSupportActionBar()!= null){
                getSupportActionBar().setTitle(movie.getTitle());
            }
            mTitle.setText(movie.getOriginalTitle());
            mSynopsis.setText(movie.getOverview());
            mReleaseDate.setText(movie.getReleaseDate("MMM dd, yyyy"));
            mVoteCount.setText(String.valueOf(movie.getVoteCount()) );
            mRatingBar.setRating((float)movie.getVoteAverage()/10 );
            mRate.setText(String.valueOf(movie.getVoteAverage()));
            mToolbar.setTitle(movie.getTitle());

            Picasso.with(this)
                    .load(movie.getBackdropUri("w500").toString())
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.poster_default)
                    .error(R.drawable.poster_default)
                    .into(mBackdrop);

            Picasso.with(this)
                    .load(movie.getPosterUri("w500").toString())
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.poster_default)
                    .error(R.drawable.poster_default)
                    .into(mPoster);
        }
    }
}
