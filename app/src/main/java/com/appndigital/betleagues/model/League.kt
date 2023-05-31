package com.appndigital.betleagues.model

import com.appndigital.betleagues.network.dto.LeagueDto

data class League(
    val id: String,
    val name: String,
    val sport: String,
) {
    companion object {
        fun fromDto(dto: LeagueDto) = League(
            id = dto.id,
            name = dto.name,
            sport = dto.sport,
        )
    }
}
