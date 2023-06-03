package com.appndigital.betleagues.model

import com.appndigital.betleagues.network.dto.TeamDto

data class Team(
    val id: String,
    val name: String,
    val logo: String,
) {
    companion object {
        fun fromDto(dto: TeamDto) = Team(
            id = dto.idTeam,
            name = dto.strTeam ?: "",
            logo = dto.strTeamBadge ?: "",
        )
    }
}
