package cl.mess.pokeinfo.home.domain.repository

import androidx.paging.PagingData
import cl.mess.pokeinfo.home.domain.result.PokemonListResult
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getPokemonList(): Flow<PagingData<PokemonListResult>>
}
