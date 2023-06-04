package com.appndigital.betleagues

import com.appndigital.betleagues.model.League
import com.appndigital.betleagues.model.Team
import com.appndigital.betleagues.ui.main.MainViewModel
import com.appndigital.betleagues.usecase.GetAllLeaguesUseCase
import com.appndigital.betleagues.usecase.GetTeamsUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class BetLeagueUnitTest {

    private lateinit var fakeRepository: FakeRepository
    private lateinit var getAllLeaguesUseCase: GetAllLeaguesUseCase
    private lateinit var getTeamsUseCase: GetTeamsUseCase
    private lateinit var viewModel: MainViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        fakeRepository = FakeRepository()
        getAllLeaguesUseCase = GetAllLeaguesUseCase(fakeRepository)
        getTeamsUseCase = GetTeamsUseCase(fakeRepository)
        viewModel = MainViewModel(getAllLeaguesUseCase, getTeamsUseCase)
    }

    @Test
    fun testLeagueLookup() = runTest {
        viewModel.loadData()

        fakeRepository.emitLeagues(
            listOf(
                League(id = "1", name = "Ligue 1", sport = "Football"),
                League(id = "2", name = "Ligue 2", sport = "Football"),
                League(id = "3", name = "National 1", sport = "Football"),
            )
        )

        viewModel.getPredictions("Ligue")
        val expected = listOf(
            League(id = "1", name = "Ligue 1", sport = "Football"),
            League(id = "2", name = "Ligue 2", sport = "Football"),
        )

        assertEquals(viewModel.predictions.value, expected)
    }

    @Test
    fun testTeamsShowup() = runTest {
        viewModel.setSelectedLeague(League(id = "1", name = "Ligue 1", sport = "Football"))

        fakeRepository.emitTeams(
            listOf(
                Team(id = "1", name = "Marseille", logo = "https://www.fakelogo.com/om"),
                Team(id = "2", name = "Lyon", logo = "https://www.fakelogo.com/ol"),
                Team(id = "3", name = "Paris", logo = "https://www.fakelogo.com/psg"),
            )
        )

        val expected = listOf(
            Team(id = "3", name = "Paris", logo = "https://www.fakelogo.com/psg"),
            Team(id = "2", name = "Lyon", logo = "https://www.fakelogo.com/ol"),
        )

        assertEquals(viewModel.teams.value, expected)
    }
}