package com.thescore.nbateamviewer.api

import com.thescore.nbateamviewer.api.dto.TeamDto
import com.thescore.nbateamviewer.domain.model.Team
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Kanghee Lee
 */
interface NbaTeamService {

    @GET("input.json")
    suspend fun listTeams(): Response<List<TeamDto>>
}