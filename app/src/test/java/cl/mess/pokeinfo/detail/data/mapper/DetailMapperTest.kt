package cl.mess.pokeinfo.detail.data.mapper

import cl.mess.pokeinfo.detail.data.source.remote.model.AbilitySlotResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.FlavorTextEntryResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.NamedApiResourceResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.OfficialArtworkResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.PokemonDetailResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.PokemonOtherSpritesResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.PokemonSpeciesResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.PokemonSpritesResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.StatResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.TypeSlotResponse
import kotlin.test.Test
import kotlin.test.assertEquals

class DetailMapperTest {

    private val mapper = DetailMapper()

    // region helpers (given)
    private fun createValidDetail() = PokemonDetailResponse(
        id = 1,
        name = "pikachu",
        height = 4,
        weight = 60,
        baseExperience = 112,
        sprites = PokemonSpritesResponse(
            other = PokemonOtherSpritesResponse(
                officialArtWork = OfficialArtworkResponse(
                    frontDefault = "default.png",
                    frontShiny = "shiny.png"
                )
            )
        ),
        types = listOf(
            TypeSlotResponse(
                slot = 1,
                type = NamedApiResourceResponse(
                    name = "electric",
                    url = "url"
                )
            )
        ),
        stats = listOf(
            StatResponse(
                baseStat = 55,
                effort = 0,
                stat = NamedApiResourceResponse(
                    name = "speed",
                    url = "url"
                )
            )
        ),
        abilities = listOf(
            AbilitySlotResponse(
                isHidden = false,
                slot = 1,
                ability = NamedApiResourceResponse(
                    name = "static",
                    url = "url"
                )
            )
        )
    )

    private fun createValidSpecies() = PokemonSpeciesResponse(
        flavorTextEntries = listOf(
            FlavorTextEntryResponse(
                flavorText = "A mouse pokemon\nvery fast",
                language = NamedApiResourceResponse(
                    name = "en",
                    url = "url"
                )
            )
        )
    )
    // endregion

    // region success test
    @Test
    fun `given valid responses when mapping then returns PokemonDetail`() {
        // GIVEN
        val detail = createValidDetail()
        val species = createValidSpecies()

        // WHEN
        val result = mapper.toDomain(detail, species)

        // THEN
        assertEquals(1, result.id)
        assertEquals("pikachu", result.name)
        assertEquals("A mouse pokemon very fast", result.description)
        assertEquals(4, result.height)
        assertEquals(60, result.weight)

        assertEquals("default.png", result.images.frontDefault)
        assertEquals("shiny.png", result.images.frontShiny)

        assertEquals(1, result.types.size)
        assertEquals("electric", result.types.first().name)

        assertEquals(1, result.stats.size)
        assertEquals("speed", result.stats.first().name)

        assertEquals(1, result.abilities.size)
        assertEquals("static", result.abilities.first().name)
    }
    // endregion

    // region validation tests

    @Test(expected = IllegalArgumentException::class)
    fun `given null id when mapping then throws exception`() {
        // GIVEN
        val detail = createValidDetail().copy(id = null)
        val species = createValidSpecies()

        // WHEN
        mapper.toDomain(detail, species)

        // THEN -> exception
    }

    @Test(expected = IllegalArgumentException::class)
    fun `given blank name when mapping then throws exception`() {
        // GIVEN
        val detail = createValidDetail().copy(name = "")
        val species = createValidSpecies()

        // WHEN
        mapper.toDomain(detail, species)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `given missing description when mapping then throws exception`() {
        // GIVEN
        val detail = createValidDetail()
        val species = createValidSpecies().copy(
            flavorTextEntries = emptyList()
        )

        // WHEN
        mapper.toDomain(detail, species)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `given null image when mapping then throws exception`() {
        // GIVEN
        val detail = createValidDetail().copy(
            sprites = createValidDetail().sprites?.copy(
                other = createValidDetail().sprites?.other?.copy(
                    officialArtWork = createValidDetail().sprites?.other?.officialArtWork?.copy(
                        frontDefault = null
                    )
                )
            )
        )
        val species = createValidSpecies()

        // WHEN
        mapper.toDomain(detail, species)
    }

    // endregion
}
