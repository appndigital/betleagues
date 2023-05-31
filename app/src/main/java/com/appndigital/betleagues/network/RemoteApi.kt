package com.appndigital.betleagues.network

import com.appndigital.betleagues.network.dto.ResponseLeagueDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApi {
    @GET("all_leagues.php")
    suspend fun getLeagues(): ResponseLeagueDto

    @GET("search_all_teams.php")
    suspend fun searchLeague(@Query("l") league: String): ResponseLeagueDto
}