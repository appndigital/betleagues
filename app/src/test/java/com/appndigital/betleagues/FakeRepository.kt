package com.appndigital.betleagues

import com.appndigital.betleagues.model.League
import com.appndigital.betleagues.model.Team
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

class FakeRepository: BetLeagueRepository {
    private val flowLeagues = MutableSharedFlow<List<League>>()
    suspend fun emitLeagues(value: List<League>) = flowLeagues.emit(value)

    private val flowTeams = MutableSharedFlow<List<Team>>()
    suspend fun emitTeams(value: List<Team>) = flowTeams.emit(value)

    override suspend fun getLeagues(): Flow<List<League>> = flowLeagues

    override suspend fun searchLeague(league: String): Flow<List<Team>> = flowTeams
}