package com.appndigital.betleagues.di

import com.appndigital.betleagues.BetLeagueRepository
import com.appndigital.betleagues.Constants.Companion.NETWORK
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BetLeagueModule {

    @Singleton
    @Provides
    @Named(NETWORK)
    fun provideBetLeagueNetworkRepository(repository: BetLeagueRepository.Network): BetLeagueRepository = repository

}