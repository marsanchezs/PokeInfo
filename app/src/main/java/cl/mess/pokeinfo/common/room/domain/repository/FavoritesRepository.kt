package cl.mess.pokeinfo.common.room.domain.repository

import cl.mess.pokeinfo.detail.domain.model.PokemonDetail
import cl.mess.pokeinfo.home.domain.model.Pokemon

interface FavoritesRepository {
    suspend fun add(pokemon: PokemonDetail)
    suspend fun delete(pokemon: PokemonDetail)
    suspend fun getAll(): List<Pokemon>
    suspend fun isFavorite(pokemonName: String): Boolean
}
