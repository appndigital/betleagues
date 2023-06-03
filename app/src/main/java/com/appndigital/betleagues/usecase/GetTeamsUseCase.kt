package com.appndigital.betleagues.usecase

import com.appndigital.betleagues.BetLeagueRepository
import com.appndigital.betleagues.Constants
import com.appndigital.betleagues.core.FlowUseCase
import com.appndigital.betleagues.model.League
import com.appndigital.betleagues.model.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Named

class GetTeamsUseCase @Inject constructor(
    @Named(Constants.NETWORK) private val networkRepository: BetLeagueRepository,
) : FlowUseCase<GetTeamsUseCase.Params, List<Team>>() {

    data class Params(val league: League)

    override suspend fun run(params: Params): Flow<List<Team>> =
        networkRepository.searchLeague(league = params.league.name)
            .flowOn(Dispatchers.IO)
}