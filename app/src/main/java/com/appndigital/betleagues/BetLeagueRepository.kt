package com.appndigital.betleagues

import com.appndigital.betleagues.model.League
import com.appndigital.betleagues.network.RemoteApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface BetLeagueRepository {
    suspend fun getLeagues(): Flow<List<League>>

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
    }
}