package cl.mess.pokeinfo.detail.data.mapper

import cl.mess.pokeinfo.detail.data.source.remote.model.AbilitySlotResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.NamedApiResourceResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.OfficialArtworkResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.PokemonDetailResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.PokemonOtherSpritesResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.PokemonSpritesResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.StatResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.TypeSlotResponse
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DetailMapperTest {

    private lateinit var mapper: DetailMapper

    @BeforeTest
    fun setUp() {
        mapper = DetailMapper()
    }

    @Test
    fun `given valid PokemonDetailResponse, when toDomain is called, then returns expected PokemonDetail`() {
        // Given
        val response = PokemonDetailResponse(
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

        // When
        val result = mapper.toDomain(response)

        // Then
        assertEquals(1, result.id)
        assertEquals("Pikachu", result.name)
        assertEquals(4, result.height)
        assertEquals(60, result.weight)
        assertEquals(112, result.baseExperience)
        assertEquals("front.png", result.images.frontDefault)
        assertEquals("front_shiny.png", result.images.frontShiny)
        assertEquals("electric", result.types.first().name)
        assertEquals("speed", result.stats.first().name)
        assertEquals("static", result.abilities.first().name)
    }

    @Test
    fun `given PokemonDetailResponse with null name, when toDomain is called, then throws exception`() {
        // Given
        val response = PokemonDetailResponse(
            id = 1,
            name = null,
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
            types = listOf(TypeSlotResponse(slot = 1, type = NamedApiResourceResponse(name = "electric", url = "url"))),
            stats = listOf(StatResponse(baseStat = 35, effort = 0, stat = NamedApiResourceResponse(name = "speed", url = "url"))),
            abilities = listOf(AbilitySlotResponse(isHidden = false, slot = 1, ability = NamedApiResourceResponse(name = "static", url = "url")))
        )

        // Then
        assertFailsWith<IllegalArgumentException> {
            // When
            mapper.toDomain(response)
        }
    }

    @Test
    fun `given PokemonDetailResponse with null sprites, when toDomain is called, then throws exception`() {
        val response = PokemonDetailResponse(
            id = 1,
            name = "Pikachu",
            height = 4,
            weight = 60,
            baseExperience = 112,
            sprites = null,
            types = listOf(TypeSlotResponse(slot = 1, type = NamedApiResourceResponse(name = "electric", url = "url"))),
            stats = listOf(StatResponse(baseStat = 35, effort = 0, stat = NamedApiResourceResponse(name = "speed", url = "url"))),
            abilities = listOf(AbilitySlotResponse(isHidden = false, slot = 1, ability = NamedApiResourceResponse(name = "static", url = "url")))
        )

        assertFailsWith<IllegalArgumentException> {
            mapper.toDomain(response)
        }
    }
}
