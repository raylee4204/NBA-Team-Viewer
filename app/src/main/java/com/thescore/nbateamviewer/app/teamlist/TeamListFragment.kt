package com.thescore.nbateamviewer.app.teamlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.assignment.vo.Status
import com.thescore.nbateamviewer.R
import kotlinx.android.synthetic.main.fragment_team_list.lst_teams
import kotlinx.android.synthetic.main.fragment_team_list.progress_bar
import kotlinx.android.synthetic.main.fragment_team_list.txt_response_error
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Kanghee Lee
 */
class TeamListFragment : Fragment(R.layout.fragment_team_list) {

    private val viewModel: TeamListViewModel by viewModel()
    private val adapter = TeamListAdapter()
    private val dataObserver = object : RecyclerView.AdapterDataObserver() {
        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            lst_teams.scrollToPosition(0)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter.registerAdapterDataObserver(dataObserver)
        viewModel.teams.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.LOADING) progress_bar.show() else progress_bar.hide()
            txt_response_error.isVisible = it.status == Status.ERROR

            it.data?.let { teams ->
                adapter.submitList(teams)
            }

            it.message?.let { message ->
                txt_response_error.text = message
            }

        })
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lst_teams.layoutManager = LinearLayoutManager(requireActivity())
        lst_teams.addItemDecoration(
            DividerItemDecoration(
                requireActivity(),
                LinearLayoutManager.VERTICAL
            )
        )
        lst_teams.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter.unregisterAdapterDataObserver(dataObserver)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_team_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_menu_sort_alphabetic -> {
                viewModel.sortByAlphabetic()
                return true
            }
            R.id.action_menu_sort_wins -> {
                viewModel.sortByWins()
                return true
            }
            R.id.action_menu_sort_losses -> {
                viewModel.sortByLosses()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}