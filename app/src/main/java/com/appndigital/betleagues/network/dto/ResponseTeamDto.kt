package com.appndigital.betleagues.network.dto

import com.squareup.moshi.Json

data class ResponseTeamDto(
    @Json(name = "teams")
    val teams: List<TeamDto>,
)