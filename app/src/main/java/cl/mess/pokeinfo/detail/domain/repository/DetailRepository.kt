package cl.mess.pokeinfo.detail.domain.repository

import cl.mess.pokeinfo.detail.domain.result.PokemonDetailResult
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    suspend fun getPokemonDetail(id: Int): Flow<PokemonDetailResult>
}
