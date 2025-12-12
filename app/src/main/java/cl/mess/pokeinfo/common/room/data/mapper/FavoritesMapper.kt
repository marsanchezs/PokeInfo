package cl.mess.pokeinfo.common.room.data.mapper

import cl.mess.pokeinfo.common.room.data.source.local.entities.Favorite
import cl.mess.pokeinfo.detail.domain.model.PokemonDetail
import cl.mess.pokeinfo.home.domain.model.Pokemon
import javax.inject.Inject

class FavoritesMapper @Inject constructor() {

    fun PokemonDetail.toFavorite() = Favorite(
        id = id,
        number = id,
        name = name,
        imageUrl = images.frontDefault
    )

    fun toPokemonList(favorites: List<Favorite>) = favorites.map { favorite -> favorite.toPokemon() }

    fun Favorite.toPokemon() = Pokemon(
        id = id,
        number = number,
        name = name,
        imageUrl = imageUrl
    )
}
