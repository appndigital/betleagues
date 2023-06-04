package com.appndigital.betleagues.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appndigital.betleagues.R
import com.appndigital.betleagues.ui.component.AutoCompleteTextView
import com.appndigital.betleagues.ui.component.TeamGrid

@Composable
fun MainScreenUI(viewModel: MainViewModel) {
    val query by viewModel.query.collectAsState()
    val predictions by viewModel.predictions.collectAsState()
    val teams by viewModel.teams.collectAsState()

    Column(modifier = Modifier.padding(8.dp)) {
        AutoCompleteTextView(
            modifier = Modifier.fillMaxWidth(),
            query = query,
            queryLabel = stringResource(R.string.league_lookup),
            onQueryChanged = { updatedQuery ->
                viewModel.getPredictions(updatedQuery)
            },
            predictions = predictions,
            onClearClick = {
                viewModel.clearPredictions()
            },
            onDoneActionClick = {},
            onItemClick = { league ->
                viewModel.setSelectedLeague(league)
            }
        ) {
            Text(it.name, fontSize = 16.sp)
        }
        TeamGrid(teams = teams)
    }
}