package com.thescore.nbateamviewer.domain

import com.thescore.nbateamviewer.api.dto.TeamDto
import com.thescore.nbateamviewer.domain.model.Team

/**
 * Created by Kanghee Lee
 */
class TeamDtoMapper(private val playerDtoMapper: PlayerDtoMapper) {
    fun convertDto(dto: TeamDto): Team {
        return Team(dto, playerDtoMapper)
    }

    fun convertDtos(dtos: List<TeamDto>): List<Team> {
        return dtos.map { convertDto(it) }
    }
}