package com.thescore.nbateamviewer.domain

import androidx.recyclerview.widget.DiffUtil
import com.thescore.nbateamviewer.domain.model.Team

/**
 * Created by Kanghee Lee
 */
class TeamItemDiffCallback : DiffUtil.ItemCallback<Team>() {

    override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

}