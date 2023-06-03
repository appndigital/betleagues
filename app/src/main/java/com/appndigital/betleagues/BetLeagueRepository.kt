package com.appndigital.betleagues

import com.appndigital.betleagues.model.League
import com.appndigital.betleagues.model.Team
import com.appndigital.betleagues.network.RemoteApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface BetLeagueRepository {
    suspend fun getLeagues(): Flow<List<League>>
    suspend fun searchLeague(league: String): Flow<List<Team>>

    class Network @Inject constructor(
        private val service: RemoteApi
    ): BetLeagueRepository {
        override suspend fun getLeagues(): Flow<List<League>> = flow {
             emit(
                 service.getLeagues().leagues.map {
                     League.fromDto(it)
                 }
             )
        }

        override suspend fun searchLeague(league: String): Flow<List<Team>> = flow {
            emit(
                service.searchLeague(league).teams.map {
                    Team.fromDto(it)
                }
            )
        }
    }
}