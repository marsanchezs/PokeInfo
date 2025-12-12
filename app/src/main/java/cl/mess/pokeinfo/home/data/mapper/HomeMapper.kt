package cl.mess.pokeinfo.home.data.mapper

import cl.mess.pokeinfo.home.data.source.remote.model.PokemonResponse
import cl.mess.pokeinfo.home.domain.model.Pokemon
import javax.inject.Inject

class HomeMapper @Inject constructor() {

    fun toDomain(response: PokemonResponse): Pokemon {

        require(!response.name.isNullOrBlank()) { "Pokemon name is null or blank" }
        require(!response.url.isNullOrBlank()) { "Pokemon url is null or blank" }

        val id = response.url.trimEnd('/').split("/").lastOrNull()?.toIntOrNull()
            ?: throw IllegalArgumentException("Invalid Pokemon ID in URL")

        return Pokemon(
            id = id,
            number = id,
            name = response.name,
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
        )
    }
}
