package com.thescore.nbateamviewer.domain.model

import com.thescore.nbateamviewer.api.dto.TeamDto
import com.thescore.nbateamviewer.domain.PlayerDtoMapper
import java.io.Serializable

/**
 * Created by Kanghee Lee
 */
data class Team(
    val id: Int,
    val fullName: String,
    val wins: Int,
    val losses: Int,
    val players: List<Player>
) : Serializable {
    constructor(dto: TeamDto, mapper: PlayerDtoMapper) : this(
        dto.id,
        dto.fullName,
        dto.wins,
        dto.losses,
        mapper.convertDtos(dto.players)
    )
}