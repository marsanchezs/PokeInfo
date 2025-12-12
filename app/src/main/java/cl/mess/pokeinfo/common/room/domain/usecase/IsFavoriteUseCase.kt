package cl.mess.pokeinfo.common.room.domain.usecase

import cl.mess.pokeinfo.common.room.domain.repository.FavoritesRepository
import javax.inject.Inject

class IsFavoriteUseCase @Inject constructor(
    private val repository: FavoritesRepository
) {
    suspend fun execute(pokemonName: String) = repository.isFavorite(pokemonName = pokemonName)
}
