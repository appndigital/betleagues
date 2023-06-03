package com.appndigital.betleagues.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appndigital.betleagues.model.League
import com.appndigital.betleagues.model.Team
import com.appndigital.betleagues.usecase.GetAllLeaguesUseCase
import com.appndigital.betleagues.usecase.GetTeamsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllLeaguesUseCase: GetAllLeaguesUseCase,
    private val getTeamsUseCase: GetTeamsUseCase,
) : ViewModel() {

    private var _query: MutableStateFlow<String> = MutableStateFlow("")
    private var _predictions: MutableStateFlow<List<League>> =
        MutableStateFlow(arrayListOf())
    private var _teams: MutableStateFlow<List<Team>> =
        MutableStateFlow(arrayListOf())

    var query = _query.asStateFlow()
    var predictions = _predictions.asStateFlow()
    var teams = _teams.asStateFlow()

    private var leagues: List<League> = listOf()

    fun loadData() {
        viewModelScope.launch {
            getAllLeaguesUseCase.execute(Unit).fold(
                onSuccess = {
                    it.collect { list ->
                        leagues = list
                    }
                },
                onFailure = {

                }
            )
        }
    }

    fun setSelectedLeague(league: League) {
        _query.value = league.name
        _predictions.value = mutableListOf()

        viewModelScope.launch {
            getTeamsUseCase.execute(GetTeamsUseCase.Params(league)).fold(
                onSuccess = { result ->
                    result.collect { list ->
                        var index = 0
                        val finalList = mutableListOf<Team>()
                        list.sortedByDescending { it.name }.forEach { team ->
                            if (index%2 == 0) {
                                finalList.add(team)
                            }
                            index++
                        }

                        _teams.value = finalList
                    }
                },
                onFailure = {}
            )
        }
    }

    fun getPredictions(query: String) {
        _query.value = query
        _predictions.value = leagues.filter { it.name.contains(query, ignoreCase = true) }
    }

    fun clearPredictions() {
        _query.value = ""
        _predictions.value = mutableListOf()
        _teams.value = mutableListOf()
    }
}