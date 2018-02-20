package com.yudihirata.br.popmovies.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by marco.hirata on 08/03/2018.
 */

public class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<?> mList;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return (this.mList == null) ? 0 : this.mList.size();
    }

    public void setList(List<?> list) {
        if (list != null) {
            mList = list;
        }
    }
}
