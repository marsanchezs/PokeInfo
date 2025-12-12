package cl.mess.pokeinfo.detail.data.repository

import app.cash.turbine.test
import cl.mess.pokeinfo.detail.data.mapper.DetailMapper
import cl.mess.pokeinfo.detail.data.source.remote.DetailService
import cl.mess.pokeinfo.detail.data.source.remote.model.AbilitySlotResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.NamedApiResourceResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.OfficialArtworkResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.PokemonDetailResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.PokemonOtherSpritesResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.PokemonSpritesResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.StatResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.TypeSlotResponse
import cl.mess.pokeinfo.detail.domain.result.PokemonDetailResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
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
        val id = 1
        val responseBody = PokemonDetailResponse(
            id = 1,
            name = "Pikachu",
            height = 4,
            weight = 60,
            baseExperience = 112,
            sprites = PokemonSpritesResponse(
                other = PokemonOtherSpritesResponse(
                    officialArtWork = OfficialArtworkResponse(
                        frontDefault = "front.png",
                        frontShiny = "front_shiny.png"
                    )
                )
            ),
            types = listOf(
                TypeSlotResponse(
                    slot = 1,
                    type = NamedApiResourceResponse(name = "electric", url = "url")
                )
            ),
            stats = listOf(
                StatResponse(
                    baseStat = 35,
                    effort = 0,
                    stat = NamedApiResourceResponse(name = "speed", url = "url")
                )
            ),
            abilities = listOf(
                AbilitySlotResponse(
                    isHidden = false,
                    slot = 1,
                    ability = NamedApiResourceResponse(name = "static", url = "url")
                )
            )
        )

        val apiResponse = retrofit2.Response.success(responseBody)

        coEvery { api.getPokemonDetail(id) } returns apiResponse

        // When & Then
        repository.getPokemonDetail(id).test {
            val item = awaitItem()
            require(item is PokemonDetailResult.Success)
            assertEquals(1, item.pokemonDetail.id)
            assertEquals("Pikachu", item.pokemonDetail.name)
            assertEquals("front.png", item.pokemonDetail.images.frontDefault)
            awaitComplete()
        }
    }

    @Test
    fun `given API returns error, when getPokemonDetail is called, then emits Error`() = runTest {
        // Given
        val id = 1
        val apiResponse = retrofit2.Response.error<PokemonDetailResponse>(
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
        val apiResponse = retrofit2.Response.success<PokemonDetailResponse>(null)

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
