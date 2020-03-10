package com.thescore.nbateamviewer.domain

import com.thescore.nbateamviewer.api.dto.PlayerDto
import com.thescore.nbateamviewer.domain.model.Player

/**
 * Created by Kanghee Lee
 */
class PlayerDtoMapper {
    fun convertDto(dto: PlayerDto): Player {
        return Player(dto)
    }

    fun convertDtos(dtos: List<PlayerDto>): List<Player> {
        return dtos.map { convertDto(it) }
    }
}