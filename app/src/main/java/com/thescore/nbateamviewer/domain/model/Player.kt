package com.thescore.nbateamviewer.domain.model

import com.thescore.nbateamviewer.api.dto.PlayerDto
import java.io.Serializable

/**
 * Created by Kanghee Lee
 */
data class Player(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val position: String,
    val number: Int
) : Serializable {
    constructor(dto: PlayerDto) : this(
        dto.id,
        dto.firstName,
        dto.lastName,
        dto.position,
        dto.number
    )
}