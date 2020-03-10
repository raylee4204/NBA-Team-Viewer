package com.thescore.nbateamviewer.app.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.thescore.nbateamviewer.R
import com.thescore.nbateamviewer.databinding.FragmentTeamBinding
import kotlinx.android.synthetic.main.fragment_team.lst_players

/**
 * Created by Kanghee Lee
 */
class TeamFragment : Fragment() {

    private val args: TeamFragmentArgs by navArgs()
    private val adapter = PlayerListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentTeamBinding>(
            inflater,
            R.layout.fragment_team,
            container,
            false
        )

        binding.team = args.team
        binding.executePendingBindings()
        adapter.submitList(args.team.players)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lst_players.layoutManager = LinearLayoutManager(requireActivity())
        lst_players.addItemDecoration(
            DividerItemDecoration(
                requireActivity(),
                LinearLayoutManager.VERTICAL
            )
        )
        lst_players.adapter = adapter
    }
}