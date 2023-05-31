package com.appndigital.betleagues.usecase

import com.appndigital.betleagues.BetLeagueRepository
import com.appndigital.betleagues.Constants.Companion.NETWORK
import com.appndigital.betleagues.core.FlowUseCase
import com.appndigital.betleagues.model.League
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Named

class GetAllLeaguesUseCase @Inject constructor(
    @Named(NETWORK) private val networkRepository: BetLeagueRepository,
) : FlowUseCase<Unit, List<League>>() {
    override suspend fun run(params: Unit): Flow<List<League>> =
        networkRepository.getLeagues()
            .flowOn(Dispatchers.IO)
}