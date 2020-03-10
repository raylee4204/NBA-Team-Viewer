package com.thescore.nbateamviewer.app.team

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thescore.nbateamviewer.BR
import com.thescore.nbateamviewer.R
import com.thescore.nbateamviewer.app.team.PlayerListAdapter.PlayerViewHolder
import com.thescore.nbateamviewer.domain.PlayerItemDiffCallback
import com.thescore.nbateamviewer.domain.model.Player

/**
 * Created by Kanghee Lee
 */
class PlayerListAdapter : ListAdapter<Player, PlayerViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_player,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class PlayerViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var player: Player? = null

        fun bindTo(player: Player) {
            this.player = player
            binding.setVariable(BR.player, player)
            binding.executePendingBindings()
        }
    }

    companion object {
        private val DIFF_CALLBACK = PlayerItemDiffCallback()
    }
}