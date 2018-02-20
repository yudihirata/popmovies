package com.yudihirata.br.popmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yudihirata.br.popmovies.MovieViewHolder;
import com.yudihirata.br.popmovies.R;
import com.yudihirata.br.popmovies.adapters.listener.RestAdapterListener;

import models.Movie;

/**
 * Created by marco.hirata on 30/08/2017.
 */

public class RestMoviesAdapter extends BaseAdapter {
    private final Context mContext;
    final private RestAdapterListener mOnClickListener;

    public RestMoviesAdapter(Context context) {
        this.mContext = context;
        mOnClickListener = (RestAdapterListener) context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_card_view, parent, false);
        return new MovieViewHolder(mOnClickListener, view);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MovieViewHolder) holder).bind(mContext, ((Movie) mList.get(position)));
    }
}
