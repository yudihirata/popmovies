package com.yudihirata.br.popmovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by marco.hirata on 13/09/2017.
 */

public abstract class UiDetailsActivity extends AppCompatActivity {
    TextView mTitle;
    TextView mSynopsis;
    TextView mReleaseDate;
    ImageView mPoster;
    ImageView mBackdrop;
    TextView mVoteCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mTitle       = ((TextView)(findViewById(R.id.tx_title)));
        mReleaseDate = ((TextView)(findViewById(R.id.tx_date)));
        mVoteCount   = ((TextView)(findViewById(R.id.tv_vote_count)));
        mSynopsis    = ((TextView)(findViewById(R.id.tv_synopsis)));
        mBackdrop    = ((ImageView)(findViewById(R.id.iv_backdrop_poster)));
        mPoster      = ((ImageView)(findViewById(R.id.iv_poster)));
    }
}
