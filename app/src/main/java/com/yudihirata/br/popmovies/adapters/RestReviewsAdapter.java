package com.yudihirata.br.popmovies.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yudihirata.br.popmovies.R;
import com.yudihirata.br.popmovies.ReviewViewHolder;

import models.Review;

/**
 * Created by marco.hirata on 07/03/2018.
 */

public class RestReviewsAdapter extends BaseAdapter {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.reviews_card_view, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ReviewViewHolder) holder).bind((Review) mList.get(position));
    }
}
