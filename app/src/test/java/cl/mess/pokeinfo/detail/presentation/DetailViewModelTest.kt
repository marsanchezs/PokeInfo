package cl.mess.pokeinfo.detail.presentation

import cl.mess.pokeinfo.common.room.domain.usecase.AddFavoriteUseCase
import cl.mess.pokeinfo.common.room.domain.usecase.DeleteFavoriteUseCase
import cl.mess.pokeinfo.common.room.domain.usecase.IsFavoriteUseCase
import cl.mess.pokeinfo.detail.domain.model.PokemonDetail
import cl.mess.pokeinfo.detail.domain.result.PokemonDetailResult
import cl.mess.pokeinfo.detail.domain.usecase.GetPokemonDetailUseCase
import cl.mess.pokeinfo.detail.presentation.uistate.DetailUiState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    private val getPokemonDetailUseCase: GetPokemonDetailUseCase = mockk()
    private val addUseCase: AddFavoriteUseCase = mockk(relaxed = true)
    private val deleteUseCase: DeleteFavoriteUseCase = mockk(relaxed = true)
    private val isFavoriteUseCase: IsFavoriteUseCase = mockk()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        viewModel = DetailViewModel(
            getPokemonDetailUseCase = getPokemonDetailUseCase,
            addUseCase = addUseCase,
            deleteUseCase = deleteUseCase,
            isFavoriteUseCase = isFavoriteUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getPokemonDetail emits Success state`() = runTest {
        // Given
        val pokemonDetail = mockk<PokemonDetail>()
        coEvery { getPokemonDetailUseCase(1) } returns flow {
            emit(PokemonDetailResult.Success(pokemonDetail))
        }

        // When
        viewModel.getPokemonDetail(1)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val state = viewModel.uiStates().value
        assertTrue(state is DetailUiState.Success)
        assertEquals(pokemonDetail, state.pokemonDetail)
    }

    @Test
    fun `getPokemonDetail emits Error state`() = runTest {
        // Given
        coEvery { getPokemonDetailUseCase(1) } returns flow {
            emit(PokemonDetailResult.Error)
        }

        // When
        viewModel.getPokemonDetail(1)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val state = viewModel.uiStates().value
        assertTrue(state is DetailUiState.Error)
    }

    @Test
    fun `toggleFavorite adds and removes favorite correctly`() = runTest {
        // Given
        val pokemonDetail = mockk<PokemonDetail>()
        viewModel.isFavorite.value = false

        // When
        viewModel.toggleFavorite(pokemonDetail)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertTrue(viewModel.isFavorite.value)
        coVerify { addUseCase(pokemonDetail) }

        // When
        viewModel.toggleFavorite(pokemonDetail)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertFalse(viewModel.isFavorite.value)
        coVerify { deleteUseCase(pokemonDetail) }
    }

    @Test
    fun `checkIfPokemonIsFavorite updates state correctly`() = runTest {
        // Given
        coEvery { isFavoriteUseCase.execute("Pikachu") } returns true

        // When
        viewModel.checkIfPokemonIsFavorite("Pikachu")
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertTrue(viewModel.isFavorite.value)
    }
}
