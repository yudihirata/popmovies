package com.yudihirata.br.popmovie.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yudihirata.br.popmovie.MovieViewHolder;
import com.yudihirata.br.popmovie.R;

import java.util.List;

import models.Movie;

/**
 * Created by marco.hirata on 30/08/2017.
 */

public class RestAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private List<Movie> mListMovies;
    private final Context mContext;
    final private RestAdapterListener mOnClickListener;

    public RestAdapter(Context context){
        this.mContext = context;
        mOnClickListener = (RestAdapterListener)context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view               = inflater.inflate(R.layout.movie_card_view, parent, false);
        return new MovieViewHolder(mOnClickListener, view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(mContext, mListMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return (this.mListMovies ==null) ? 0:this.mListMovies.size();
    }

    public void setList(List<Movie> list){
        if (list != null ){
            mListMovies = list;
        }
    }
}
