package com.yudihirata.br.popmovies;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import models.Review;

/**
 * Created by marco.hirata on 07/03/2018.
 */

public class ReviewViewHolder extends RecyclerView.ViewHolder {
    private final TextView mTextView;

    public ReviewViewHolder(View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.tv_review);
    }

    public void bind(Review review) {
        mTextView.setText(review.getContent());
    }
}
