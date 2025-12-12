package cl.mess.pokeinfo.detail.domain.usecase

import app.cash.turbine.test
import cl.mess.pokeinfo.detail.domain.model.PokemonDetail
import cl.mess.pokeinfo.detail.domain.model.PokemonImages
import cl.mess.pokeinfo.detail.domain.repository.DetailRepository
import cl.mess.pokeinfo.detail.domain.result.PokemonDetailResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class GetPokemonDetailUseCaseTest {

    private lateinit var useCase: GetPokemonDetailUseCase
    private val repository: DetailRepository = mockk()

    @Before
    fun setUp() {
        useCase = GetPokemonDetailUseCase(repository)
    }

    @Test
    fun `given repository emits Success, when use case is invoked, then emits same Success`() = runTest {
        // Given
        val id = 1
        val fakePokemonDetail = PokemonDetail(
            id = 1,
            name = "Pikachu",
            height = 4,
            weight = 60,
            baseExperience = 112,
            images = PokemonImages(frontDefault = "front.png", frontShiny = "front_shiny.png"),
            types = emptyList(),
            stats = emptyList(),
            abilities = emptyList()
        )
        val fakeResult = PokemonDetailResult.Success(fakePokemonDetail)

        coEvery { repository.getPokemonDetail(id) } returns kotlinx.coroutines.flow.flowOf(fakeResult)

        // When & Then
        useCase(id).test {
            val item = awaitItem()
            assertTrue(item is PokemonDetailResult.Success)
            assertEquals(fakePokemonDetail.id, item.pokemonDetail.id)
            assertEquals(fakePokemonDetail.name, item.pokemonDetail.name)
            awaitComplete()
        }
    }

    @Test
    fun `given repository emits Error, when use case is invoked, then emits same Error`() = runTest {
        // Given
        val id = 1
        val fakeResult = PokemonDetailResult.Error

        coEvery { repository.getPokemonDetail(id) } returns kotlinx.coroutines.flow.flowOf(fakeResult)

        // When & Then
        useCase(id).test {
            val item = awaitItem()
            assertTrue(item is PokemonDetailResult.Error)
            awaitComplete()
        }
    }
}
