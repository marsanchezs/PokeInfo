package cl.mess.pokeinfo.home.domain.usecase

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.recyclerview.widget.ListUpdateCallback
import app.cash.turbine.test
import cl.mess.pokeinfo.home.domain.model.Pokemon
import cl.mess.pokeinfo.home.domain.repository.HomeRepository
import cl.mess.pokeinfo.home.domain.result.PokemonListResult
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class GetPokemonListUseCaseTest {

    private lateinit var repository: HomeRepository
    private lateinit var useCase: GetPokemonListUseCase

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        useCase = GetPokemonListUseCase(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given repository returns paging data, when use case is invoked, then it returns same paging data`() = runTest {
        // Given
        val pokemon = Pokemon(id = 25, number = 25, name = "pikachu", imageUrl = "url")
        val pokemonResult: PokemonListResult = PokemonListResult.Success(listOf(pokemon))
        val pagingData: PagingData<PokemonListResult> = PagingData.from(listOf(pokemonResult))
        every { repository.getPokemonList() } returns flowOf(pagingData)

        // When / Then
        useCase().test {
            val emittedPagingData = awaitItem()

            val differ = AsyncPagingDataDiffer(
                diffCallback = object : androidx.recyclerview.widget.DiffUtil.ItemCallback<PokemonListResult>() {
                    override fun areItemsTheSame(oldItem: PokemonListResult, newItem: PokemonListResult) = oldItem == newItem
                    override fun areContentsTheSame(oldItem: PokemonListResult, newItem: PokemonListResult) = oldItem == newItem
                },
                updateCallback = object : ListUpdateCallback {
                    override fun onInserted(position: Int, count: Int) {}
                    override fun onRemoved(position: Int, count: Int) {}
                    override fun onMoved(fromPosition: Int, toPosition: Int) {}
                    override fun onChanged(position: Int, count: Int, payload: Any?) {}
                },
                workerDispatcher = testDispatcher
            )

            differ.submitData(emittedPagingData)

            testDispatcher.scheduler.advanceUntilIdle()

            val firstItem = differ.snapshot().items.first() as PokemonListResult.Success
            assertEquals(listOf(pokemon), firstItem.pokemonList)

            cancelAndIgnoreRemainingEvents()
        }
    }
}
