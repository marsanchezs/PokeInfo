package cl.mess.pokeinfo.common.room.data.repository

import cl.mess.pokeinfo.common.room.data.mapper.FavoritesMapper
import cl.mess.pokeinfo.common.room.data.source.local.dao.FavoriteDao
import cl.mess.pokeinfo.common.room.data.source.local.entities.Favorite
import cl.mess.pokeinfo.detail.domain.model.PokemonDetail
import cl.mess.pokeinfo.detail.domain.model.PokemonImages
import cl.mess.pokeinfo.home.domain.model.Pokemon
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class FavoritesRepositoryImplTest {

    private lateinit var repository: FavoritesRepositoryImpl
    private val dao: FavoriteDao = mockk()
    private val mapper: FavoritesMapper = mockk()

    private val samplePokemonDetail = PokemonDetail(
        id = 1,
        name = "Pikachu",
        height = 4,
        weight = 60,
        baseExperience = 112,
        images = PokemonImages(
            frontDefault = "front_url",
            frontShiny = "shiny_url"
        ),
        types = emptyList(),
        stats = emptyList(),
        abilities = emptyList(),
        description = "description"
    )

    private val sampleFavorite = Favorite(
        id = 1,
        number = 1,
        name = "Pikachu",
        imageUrl = "front_url"
    )

    private val samplePokemon = Pokemon(
        id = 1,
        number = 1,
        name = "Pikachu",
        imageUrl = "front_url"
    )

    @Before
    fun setUp() {
        repository = FavoritesRepositoryImpl(dao, mapper)
    }

    @Test
    fun `add calls dao with mapped favorite`() = runTest {
        // Given
        coEvery { mapper.run { samplePokemonDetail.toFavorite() } } returns sampleFavorite
        coEvery { dao.add(sampleFavorite) } returns Unit

        // When
        repository.add(samplePokemonDetail)

        // Then
        coVerify(exactly = 1) { dao.add(sampleFavorite) }
    }

    @Test
    fun `delete calls dao with mapped favorite`() = runTest {
        // Given
        coEvery { mapper.run { samplePokemonDetail.toFavorite() } } returns sampleFavorite
        coEvery { dao.delete(sampleFavorite) } returns Unit

        // When
        repository.delete(samplePokemonDetail)

        // Then
        coVerify(exactly = 1) { dao.delete(sampleFavorite) }
    }

    @Test
    fun `getAll returns mapped Pokemon list`() = runTest {
        // Given
        coEvery { dao.getAll() } returns listOf(sampleFavorite)
        coEvery { mapper.toPokemonList(listOf(sampleFavorite)) } returns listOf(samplePokemon)

        // When
        val result = repository.getAll()

        // Then
        assertEquals(listOf(samplePokemon), result)
    }

    @Test
    fun `isFavorite returns true when pokemon exists`() = runTest {
        // Given
        coEvery { dao.isFavorite("Pikachu") } returns sampleFavorite

        // When
        val result = repository.isFavorite("Pikachu")

        // Then
        assertTrue(result)
    }

    @Test
    fun `isFavorite returns false when pokemon does not exist`() = runTest {
        // Given
        coEvery { dao.isFavorite("Pikachu") } returns null

        // When
        val result = repository.isFavorite("Pikachu")

        // Then
        assertFalse(result)
    }
}
