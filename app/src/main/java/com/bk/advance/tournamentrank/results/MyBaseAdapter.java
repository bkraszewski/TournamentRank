package com.bk.advance.tournamentrank.results;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bk.advance.tournamentrank.model.Player;

public abstract class MyBaseAdapter
        extends RecyclerView.Adapter<PlayerViewHolder> {
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(
                layoutInflater, viewType, parent, false);
        return new PlayerViewHolder(binding);
    }

    public void onBindViewHolder(PlayerViewHolder holder,
                                 int position) {
        Player obj = getObjForPosition(position);
        holder.bind(obj);
    }
    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    protected abstract Player getObjForPosition(int position);

    protected abstract int getLayoutIdForPosition(int position);
}
