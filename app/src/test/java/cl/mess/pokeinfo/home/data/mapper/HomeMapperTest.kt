package cl.mess.pokeinfo.home.data.mapper

import cl.mess.pokeinfo.home.data.source.remote.model.PokemonResponse
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class HomeMapperTest {

    private lateinit var mapper: HomeMapper

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mapper = HomeMapper()
    }

    @Test
    fun `toDomain maps valid PokemonResponse correctly`() {
        // Given
        val response = PokemonResponse(
            name = "pikachu",
            url = "https://pokeapi.co/api/v2/pokemon/25/"
        )

        // When
        val result = mapper.toDomain(response)

        // Then
        assertEquals(25, result.id)
        assertEquals(25, result.number)
        assertEquals("pikachu", result.name)
        assertEquals(
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
            result.imageUrl
        )
    }

    @Test
    fun `toDomain throws exception when name is blank`() {
        // Given
        val response = PokemonResponse(
            name = "  ",
            url = "https://pokeapi.co/api/v2/pokemon/25/"
        )

        // When / Then
        val exception = assertFailsWith<IllegalArgumentException> {
            mapper.toDomain(response)
        }
        assertEquals("Pokemon name is null or blank", exception.message)
    }

    @Test
    fun `toDomain throws exception when url is blank`() {
        // Given
        val response = PokemonResponse(
            name = "pikachu",
            url = " "
        )

        // When / Then
        val exception = assertFailsWith<IllegalArgumentException> {
            mapper.toDomain(response)
        }
        assertEquals("Pokemon url is null or blank", exception.message)
    }

    @Test
    fun `toDomain throws exception when url has invalid id`() {
        // Given
        val response = PokemonResponse(
            name = "pikachu",
            url = "https://pokeapi.co/api/v2/pokemon/abc/"
        )

        // When / Then
        val exception = assertFailsWith<IllegalArgumentException> {
            mapper.toDomain(response)
        }
        assertEquals("Invalid Pokemon ID in URL", exception.message)
    }
}
