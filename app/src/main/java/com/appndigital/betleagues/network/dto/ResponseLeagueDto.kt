package com.appndigital.betleagues.network.dto

import com.squareup.moshi.Json

data class ResponseLeagueDto(
    @Json(name = "leagues")
    val leagues: List<LeagueDto>,
)
