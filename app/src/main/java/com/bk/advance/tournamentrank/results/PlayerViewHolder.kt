package com.bk.advance.tournamentrank.results

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bk.advance.tournamentrank.BR
import com.bk.advance.tournamentrank.model.Player

class PlayerViewHolder( val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(player: Player){
        binding.setVariable(BR.player, player)
        binding.executePendingBindings()
    }
}
