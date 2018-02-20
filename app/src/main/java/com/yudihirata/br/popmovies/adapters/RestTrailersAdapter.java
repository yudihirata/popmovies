package com.yudihirata.br.popmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yudihirata.br.popmovies.R;
import com.yudihirata.br.popmovies.TrailerViewHolder;
import com.yudihirata.br.popmovies.adapters.listener.RestAdapterTrailersListener;

import models.Trailer;

/**
 * Created by marco.hirata on 20/02/2018.
 */

public class RestTrailersAdapter extends BaseAdapter {

    final private RestAdapterTrailersListener mOnClickListener;

    public RestTrailersAdapter(Context context) {
        mOnClickListener = (RestAdapterTrailersListener) context;
    }

    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.trailler_card_view, parent,
                false);
        return new TrailerViewHolder(mOnClickListener, view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TrailerViewHolder) holder).bind((Trailer) mList.get(position));
    }


}
