package com.thescore.nbateamviewer.app.teamlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.squareup.assignment.vo.Resource
import com.thescore.nbateamviewer.api.NbaTeamApi
import com.thescore.nbateamviewer.vo.TeamSort
import com.thescore.nbateamviewer.vo.TeamSort.ALPHABETIC
import com.thescore.nbateamviewer.vo.TeamSort.LOSSES
import com.thescore.nbateamviewer.vo.TeamSort.WINS

/**
 * Created by Kanghee Lee
 */
class TeamListViewModel(private val api: NbaTeamApi) : ViewModel() {

    private val sort: MutableLiveData<TeamSort> = MutableLiveData()
    val teams = sort.switchMap {
        liveData {
            emit(Resource.loading())
            val response = api.listTeams(it)
            emit(response)
        }
    }

    init {
        sort.value = ALPHABETIC
    }

    fun sortByAlphabetic() {
        sort(ALPHABETIC)
    }

    fun sortByWins() {
        sort(WINS)
    }

    fun sortByLosses() {
        sort(LOSSES)
    }

    private fun sort(teamSort: TeamSort) {
        val current = sort.value
        if (current == teamSort) {
            return
        }

        sort.value = teamSort
    }
}