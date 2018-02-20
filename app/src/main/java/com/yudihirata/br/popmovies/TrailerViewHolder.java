package com.yudihirata.br.popmovies;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yudihirata.br.popmovies.adapters.listener.RestAdapterTrailersListener;

import models.Trailer;

/**
 * Created by marco.hirata on 20/02/2018.
 */

public class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final ImageView mImageView;
    private final RestAdapterTrailersListener mOnClickListener;
    private Trailer mTrailer;

    public TrailerViewHolder(RestAdapterTrailersListener mOnClickListener, View itemView) {
        super(itemView);
        this.mOnClickListener = mOnClickListener;
        itemView.setOnClickListener(this);
        this.mImageView = itemView.findViewById(R.id.iv_thumbnail);

    }

    @Override
    public void onClick(View view) {
        mOnClickListener.onTrailerClick(mTrailer);
    }

    public void bind(Trailer trailer) {
        mTrailer = trailer;
        Picasso.with(mImageView.getContext())
                .load(mTrailer.getThumbnail(Trailer.THUMBNAIL_STANDARD).toString())
                .fit()
                .placeholder(R.drawable.poster_default)
                .error(R.drawable.poster_default)
                .into(mImageView);
    }
}
