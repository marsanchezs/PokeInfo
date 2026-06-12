package cl.mess.pokeinfo.common.room.domain.usecase

import cl.mess.pokeinfo.common.room.domain.repository.FavoritesRepository
import cl.mess.pokeinfo.detail.domain.model.PokemonDetail
import cl.mess.pokeinfo.detail.domain.model.PokemonImages
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AddFavoriteUseCaseTest {

    private lateinit var useCase: AddFavoriteUseCase
    private val repository: FavoritesRepository = mockk()

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

    @Before
    fun setUp() {
        useCase = AddFavoriteUseCase(repository)
    }

    @Test
    fun `invoke calls repository add with given PokemonDetail`() = runTest {
        // Given
        coEvery { repository.add(samplePokemonDetail) } returns Unit

        // When
        useCase(samplePokemonDetail)

        // Then
        coVerify(exactly = 1) { repository.add(samplePokemonDetail) }
    }
}
