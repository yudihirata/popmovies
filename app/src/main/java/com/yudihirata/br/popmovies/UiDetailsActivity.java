package com.yudihirata.br.popmovies;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by marco.hirata on 13/09/2017.
 */

public abstract class UiDetailsActivity extends AppCompatActivity implements
        SwipeRefreshLayout.OnRefreshListener {
    private static final int COLUMNS = 1;
    SwipeRefreshLayout mSwipeRefreshLayout;
    TextView mTitle;
    TextView mSynopsis;
    TextView mRate;
    TextView mReleaseDate;
    ImageView mPoster;
    ImageView mBackdrop;
    TextView mVoteCount;
    RatingBar mRatingBar;
    Toolbar mToolbar;
    RecyclerView mRecyclerViewTrailers;
    RecyclerView mRecyclerViewReviews;
    FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mTitle = findViewById(R.id.tx_title);
        mReleaseDate = findViewById(R.id.tx_date);
        mVoteCount = findViewById(R.id.tv_vote_count);
        mRate = findViewById(R.id.tv_rate);
        mSynopsis = findViewById(R.id.tv_synopsis);
        mBackdrop = findViewById(R.id.iv_backdrop_poster);
        mPoster = findViewById(R.id.iv_poster);
        mRatingBar = findViewById(R.id.rb_rate);
        mToolbar = findViewById(R.id.toolbar);

        /*Recycler view Trailers*/
        mRecyclerViewTrailers = findViewById(R.id.rv_trailers);
        mRecyclerViewTrailers.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        mRecyclerViewTrailers.setHasFixedSize(true);

        /*Recycler view Reviews*/
        mRecyclerViewReviews = findViewById(R.id.rv_reviews);
        mRecyclerViewReviews.setLayoutManager(new GridLayoutManager(this, COLUMNS));
        mRecyclerViewReviews.setHasFixedSize(true);
        mSwipeRefreshLayout = findViewById(R.id.activity_reviews_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        /*FloatingActionButton*/
        mFloatingActionButton = findViewById(R.id.fa_favorite);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickFloatingActionButton(view);
            }
        });

    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    void onClickFloatingActionButton(View view) {
    }
}
