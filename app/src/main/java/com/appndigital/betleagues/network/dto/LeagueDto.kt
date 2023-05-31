package com.appndigital.betleagues.network.dto

import com.squareup.moshi.Json

data class LeagueDto(
    @Json(name = "idLeague")
    val id: String,
    @Json(name = "strLeague")
    val name: String,
    @Json(name = "strSport")
    val sport: String,
    @Json(name = "strLeagueAlternate")
    val alternate: String?,
)
