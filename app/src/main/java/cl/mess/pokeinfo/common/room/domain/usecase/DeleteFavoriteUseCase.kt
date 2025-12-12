package cl.mess.pokeinfo.common.room.domain.usecase

import cl.mess.pokeinfo.common.room.domain.repository.FavoritesRepository
import cl.mess.pokeinfo.detail.domain.model.PokemonDetail
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(pokemon: PokemonDetail) = repository.delete(pokemon = pokemon)
}
