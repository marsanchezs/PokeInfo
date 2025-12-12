package cl.mess.pokeinfo.detail.domain.result

import cl.mess.pokeinfo.detail.domain.model.PokemonDetail

sealed class PokemonDetailResult {
    data class Success(val pokemonDetail: PokemonDetail): PokemonDetailResult()
    data object Error: PokemonDetailResult()
}
