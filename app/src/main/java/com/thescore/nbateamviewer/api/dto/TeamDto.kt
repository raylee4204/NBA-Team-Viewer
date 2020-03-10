package com.thescore.nbateamviewer.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Kanghee Lee
 */
@JsonClass(generateAdapter = true)
data class TeamDto(
    val id: Int,
    @Json(name = "full_name") val fullName: String,
    val wins: Int,
    val losses: Int,
    val players: List<PlayerDto>
)