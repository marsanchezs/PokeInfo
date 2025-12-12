package cl.mess.pokeinfo.common.room.domain.usecase

import cl.mess.pokeinfo.common.room.domain.repository.FavoritesRepository
import cl.mess.pokeinfo.home.domain.model.Pokemon
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class GetAllFavoritesUseCaseTest {

    private lateinit var useCase: GetAllFavoritesUseCase
    private val repository: FavoritesRepository = mockk()

    private val samplePokemonList = listOf(
        Pokemon(
            id = 1,
            number = 1,
            name = "Pikachu",
            imageUrl = "front_url"
        ),
        Pokemon(
            id = 2,
            number = 2,
            name = "Bulbasaur",
            imageUrl = "front_url_2"
        )
    )

    @Before
    fun setUp() {
        useCase = GetAllFavoritesUseCase(repository)
    }

    @Test
    fun `invoke returns list of favorite Pokemon`() = runTest {
        // Given
        coEvery { repository.getAll() } returns samplePokemonList

        // When
        val result = useCase()

        // Then
        assertEquals(samplePokemonList, result)
        coVerify(exactly = 1) { repository.getAll() }
    }
}
