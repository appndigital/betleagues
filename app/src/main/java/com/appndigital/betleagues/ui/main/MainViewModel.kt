package com.appndigital.betleagues.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appndigital.betleagues.usecase.GetAllLeaguesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllLeaguesUseCase: GetAllLeaguesUseCase
) : ViewModel() {

    fun loadData() {
        viewModelScope.launch {
            getAllLeaguesUseCase.execute(Unit).fold(
                onSuccess = {
                    it.collect { list ->
                        Log.d("TAG", "list: ${list.size}")
                    }
                },
                onFailure = {

                }
            )
        }
    }
}