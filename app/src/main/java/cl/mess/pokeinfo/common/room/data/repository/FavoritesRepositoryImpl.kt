package cl.mess.pokeinfo.common.room.data.repository

import cl.mess.pokeinfo.common.room.data.mapper.FavoritesMapper
import cl.mess.pokeinfo.common.room.data.source.local.dao.FavoriteDao
import cl.mess.pokeinfo.common.room.domain.repository.FavoritesRepository
import cl.mess.pokeinfo.detail.domain.model.PokemonDetail
import cl.mess.pokeinfo.home.domain.model.Pokemon
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val dao: FavoriteDao,
    private val mapper: FavoritesMapper
) : FavoritesRepository {

    override suspend fun add(pokemon: PokemonDetail) {
        val favorite = with(receiver = mapper) { pokemon.toFavorite() }
        dao.add(pokemon = favorite)
    }

    override suspend fun delete(pokemon: PokemonDetail) {
        val favorite = with(receiver = mapper) { pokemon.toFavorite() }
        dao.delete(pokemon = favorite)
    }

    override suspend fun getAll(): List<Pokemon> {
        val favorites = dao.getAll()
        val pokemonList = mapper.toPokemonList(favorites = favorites)
        return pokemonList
    }

    override suspend fun isFavorite(pokemonName: String): Boolean {
        val favoritePokemon = dao.isFavorite(pokemonName)
        return favoritePokemon != null
    }
}
