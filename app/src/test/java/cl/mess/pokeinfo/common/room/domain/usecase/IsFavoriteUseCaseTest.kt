package cl.mess.pokeinfo.common.room.domain.usecase

import cl.mess.pokeinfo.common.room.domain.repository.FavoritesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class IsFavoriteUseCaseTest {

    private lateinit var useCase: IsFavoriteUseCase
    private val repository: FavoritesRepository = mockk()

    @Before
    fun setUp() {
        useCase = IsFavoriteUseCase(repository)
    }

    @Test
    fun `execute returns true when Pokemon is favorite`() = runTest {
        // Given
        val pokemonName = "Pikachu"
        coEvery { repository.isFavorite(pokemonName) } returns true

        // When
        val result = useCase.execute(pokemonName)

        // Then
        assertTrue(result)
        coVerify(exactly = 1) { repository.isFavorite(pokemonName) }
    }

    @Test
    fun `execute returns false when Pokemon is not favorite`() = runTest {
        // Given
        val pokemonName = "Bulbasaur"
        coEvery { repository.isFavorite(pokemonName) } returns false

        // When
        val result = useCase.execute(pokemonName)

        // Then
        assertFalse(result)
        coVerify(exactly = 1) { repository.isFavorite(pokemonName) }
    }
}
