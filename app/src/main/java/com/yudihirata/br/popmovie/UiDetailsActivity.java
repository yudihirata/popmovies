package com.yudihirata.br.popmovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by marco.hirata on 13/09/2017.
 */

public abstract class UiDetailsActivity extends AppCompatActivity {
    TextView mTitle;
    TextView mSynopsis;
    TextView mRate;
    TextView mReleaseDate;
    ImageView mPoster;
    ImageView mBackdrop;
    TextView mVoteCount;
    RatingBar mRatingBar;
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mTitle       = ((TextView)(findViewById(R.id.tx_title)));
        mReleaseDate = ((TextView)(findViewById(R.id.tx_date)));
        mVoteCount   = ((TextView)(findViewById(R.id.tv_vote_count)));
        mRate        = ((TextView)(findViewById(R.id.tv_rate)));
        mSynopsis    = ((TextView)(findViewById(R.id.tv_synopsis)));
        mBackdrop    = ((ImageView)(findViewById(R.id.iv_backdrop_poster)));
        mPoster      = ((ImageView)(findViewById(R.id.iv_poster)));
        mRatingBar   = ((RatingBar)(findViewById(R.id.rb_rate)));
        mToolbar     = ((Toolbar)(findViewById(R.id.toolbar)));
    }
}
