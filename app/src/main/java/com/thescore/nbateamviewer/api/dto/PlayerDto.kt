package com.thescore.nbateamviewer.api.dto

import com.squareup.moshi.Json

/**
 * Created by Kanghee Lee
 */
data class PlayerDto(
    val id: Int,
    @Json(name = "first_name") val firstName: String,
    @Json(name = "last_name") val lastName: String,
    val position: String,
    val number: Int
)