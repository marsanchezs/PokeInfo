package cl.mess.pokeinfo.detail.data.repository

import app.cash.turbine.test
import cl.mess.pokeinfo.detail.data.mapper.DetailMapper
import cl.mess.pokeinfo.detail.data.source.remote.DetailService
import cl.mess.pokeinfo.detail.data.source.remote.model.PokemonDetailResponse
import cl.mess.pokeinfo.detail.domain.result.PokemonDetailResult
import cl.mess.pokeinfo.detail.factory.DetailFactory.makePokemonDetailResponse
import cl.mess.pokeinfo.detail.factory.DetailFactory.makePokemonSpeciesResponse
import cl.mess.pokeinfo.utils.RandomFactory
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class DetailRepositoryImplTest {

    private lateinit var repository: DetailRepositoryImpl
    private val api: DetailService = mockk()
    private val mapper: DetailMapper = DetailMapper()

    @Before
    fun setUp() {
        repository = DetailRepositoryImpl(api, mapper)
    }

    @Test
    fun `given successful API response, when getPokemonDetail is called, then emits Success`() = runTest {
        // Given
        val id = RandomFactory.generateRandomInt()
        val name = RandomFactory.generateRandomString()
        val frontDefault = RandomFactory.generateRandomString()

        val responseBody = makePokemonDetailResponse(
            id = id.toString(),
            name = name,
            frontDefault = frontDefault
        )

        val speciesResponseBody = makePokemonSpeciesResponse()

        val apiResponse = Response.success(responseBody)

        coEvery { api.getPokemonDetail(id) } returns apiResponse

        coEvery {
            api.getPokemonSpecies(id)
        } returns Response.success(speciesResponseBody)

        // When & Then
        repository.getPokemonDetail(id).test {
            val item = awaitItem()
            require(item is PokemonDetailResult.Success)
            assertEquals(id, item.pokemonDetail.id)
            assertEquals(name, item.pokemonDetail.name)
            assertEquals(frontDefault, item.pokemonDetail.images.frontDefault)
            awaitComplete()
        }
    }

    @Test
    fun `given API returns error, when getPokemonDetail is called, then emits Error`() = runTest {
        // Given
        val id = 1
        val apiResponse = Response.error<PokemonDetailResponse>(
            404,
            "".toResponseBody(null)
        )

        coEvery { api.getPokemonDetail(id) } returns apiResponse

        // When & Then
        repository.getPokemonDetail(id).test {
            val item = awaitItem()
            require(item is PokemonDetailResult.Error)
            awaitComplete()
        }
    }

    @Test
    fun `given API returns null body, when getPokemonDetail is called, then emits Error`() = runTest {
        // Given
        val id = 1
        val apiResponse = Response.success<PokemonDetailResponse>(null)

        coEvery { api.getPokemonDetail(id) } returns apiResponse

        // When & Then
        repository.getPokemonDetail(id).test {
            val item = awaitItem()
            require(item is PokemonDetailResult.Error)
            awaitComplete()
        }
    }

    @Test
    fun `given exception is thrown, when getPokemonDetail is called, then emits Error`() = runTest {
        // Given
        val id = 1
        coEvery { api.getPokemonDetail(id) } throws RuntimeException("Network failure")

        // When & Then
        repository.getPokemonDetail(id).test {
            val item = awaitItem()
            require(item is PokemonDetailResult.Error)
            awaitComplete()
        }
    }
}
