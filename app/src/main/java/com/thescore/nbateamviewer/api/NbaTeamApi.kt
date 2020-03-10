package com.thescore.nbateamviewer.api

import com.squareup.assignment.vo.Resource
import com.thescore.nbateamviewer.domain.TeamDtoMapper
import com.thescore.nbateamviewer.domain.model.Team
import com.thescore.nbateamviewer.vo.TeamSort
import com.thescore.nbateamviewer.vo.TeamSort.ALPHABETIC
import com.thescore.nbateamviewer.vo.TeamSort.LOSSES
import com.thescore.nbateamviewer.vo.TeamSort.WINS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Kanghee Lee
 */
class NbaTeamApi(
    private val service: NbaTeamService,
    private val teamDtoMapper: TeamDtoMapper
) {

    suspend fun listTeams(sort: TeamSort): Resource<List<Team>> {
        return withContext(Dispatchers.Default) {
            val response = service.listTeams()
            if (response.isSuccessful) {
                response.body()?.let {
                    val teams = when (sort) {
                        ALPHABETIC -> it.sortedBy { team -> team.fullName }
                        WINS -> it.sortedByDescending { team -> team.wins }
                        LOSSES -> it.sortedByDescending { team -> team.losses }
                    }
                    return@withContext Resource.success(teamDtoMapper.convertDtos(teams))
                }
            }

            Resource.error(response.message(), emptyList<Team>())
        }
    }

    companion object {
        private const val JSON_FILE_NAME = "input.json"
    }
}