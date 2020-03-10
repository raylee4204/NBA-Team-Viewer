package com.thescore.nbateamviewer.app.teamlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thescore.nbateamviewer.BR
import com.thescore.nbateamviewer.R
import com.thescore.nbateamviewer.app.teamlist.TeamListAdapter.TeamListViewHolder
import com.thescore.nbateamviewer.domain.TeamItemDiffCallback
import com.thescore.nbateamviewer.domain.model.Team

/**
 * Created by Kanghee Lee
 */
class TeamListAdapter : ListAdapter<Team, TeamListViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamListViewHolder {
        return TeamListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_team, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TeamListViewHolder, position: Int) {
        holder.itemView
        holder.bindTo(getItem(position))
    }

    class TeamListViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var team: Team? = null

        init {
            itemView.setOnClickListener {
                team?.let { team ->
                    it.findNavController().navigate(TeamListFragmentDirections.actionToTeam(team))
                }
            }
        }

        fun bindTo(team: Team) {
            this.team = team
            binding.setVariable(BR.team, team)
            binding.executePendingBindings()
        }
    }

    companion object {
        private val DIFF_CALLBACK = TeamItemDiffCallback()
    }

}