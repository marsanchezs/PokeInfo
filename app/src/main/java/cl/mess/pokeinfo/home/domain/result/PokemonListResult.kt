package cl.mess.pokeinfo.home.domain.result

import cl.mess.pokeinfo.home.domain.model.Pokemon

sealed class PokemonListResult {
    data class Success(val pokemonList: List<Pokemon>): PokemonListResult()
    data object Error: PokemonListResult()
}
