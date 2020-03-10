package com.thescore.nbateamviewer.domain

import androidx.recyclerview.widget.DiffUtil
import com.thescore.nbateamviewer.domain.model.Player

/**
 * Created by Kanghee Lee
 */
class PlayerItemDiffCallback : DiffUtil.ItemCallback<Player>() {
    override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

}