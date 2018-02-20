package com.yudihirata.br.popmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yudihirata.br.popmovies.adapters.listener.RestAdapterListener;

import models.Movie;

/**
 * Created by marco.hirata on 30/08/2017.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final ImageView mImageView;
    private final RestAdapterListener mOnClickListener;
    private Movie mMovie;


    public MovieViewHolder(RestAdapterListener OnClickListener, View itemView) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.iv_backdrop_poster);
        itemView.setOnClickListener(this);
        mOnClickListener = OnClickListener;
    }

    public void bind(Context context, Movie movie) {

        mMovie = movie;
        Picasso.with(context)
                .load(movie.getPosterUri().toString())
                .fit()
                .placeholder(R.drawable.poster_default)
                .error(R.drawable.poster_default)
                .into(mImageView);
    }

    @Override
    public void onClick(View view) {
        mOnClickListener.onItemClick(mMovie);
    }
}
