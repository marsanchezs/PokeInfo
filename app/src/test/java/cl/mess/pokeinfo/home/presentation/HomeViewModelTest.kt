package cl.mess.pokeinfo.home.presentation

import androidx.paging.PagingData
import cl.mess.pokeinfo.common.room.domain.usecase.GetAllFavoritesUseCase
import cl.mess.pokeinfo.home.domain.model.Pokemon
import cl.mess.pokeinfo.home.domain.usecase.GetPokemonListUseCase
import cl.mess.pokeinfo.home.presentation.uieffect.HomeUiEffect
import cl.mess.pokeinfo.home.ui.PokemonFilter
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    private val getPokemonListUseCase: GetPokemonListUseCase = mockk()
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase = mockk()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        coEvery { getPokemonListUseCase() } returns flow { emit(PagingData.empty()) }

        viewModel = HomeViewModel(
            getPokemonListUseCase = getPokemonListUseCase,
            getAllFavoritesUseCase = getAllFavoritesUseCase
        )
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getFavorites updates favorites state`() = runTest {
        // Given
        val pokemonList = listOf(mockk<Pokemon>(), mockk<Pokemon>())
        coEvery { getAllFavoritesUseCase() } returns pokemonList

        // When
        viewModel.getFavorites()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(pokemonList, viewModel.favorites.value)
    }

    @Test
    fun `setFilter updates filter state`() = runTest {
        // Given
        val newFilter = PokemonFilter.Favorites

        // When
        viewModel.setFilter(newFilter)

        // Then
        assertEquals(newFilter, viewModel.filter.value)
    }

    @Test
    fun `showModal emits ShowModal effect`() = runTest {
        // Given / When
        val emitted = mutableListOf<HomeUiEffect>()
        val job = launch {
            viewModel.uiEffects.toList(emitted)
        }

        viewModel.showModal()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertTrue(emitted.contains(HomeUiEffect.ShowModal))
        job.cancel()
    }

    @Test
    fun `navigateToDetail emits NavigateToDetail effect`() = runTest {
        // Given / When
        val emitted = mutableListOf<HomeUiEffect>()
        val job = launch {
            viewModel.uiEffects.toList(emitted)
        }

        val id = 25
        viewModel.navigateToDetail(id)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertTrue(emitted.contains(HomeUiEffect.NavigateToDetail(id)))
        job.cancel()
    }
}
