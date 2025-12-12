package cl.mess.pokeinfo.home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import cl.mess.pokeinfo.home.data.mapper.HomeMapper
import cl.mess.pokeinfo.home.data.source.remote.HomeService
import cl.mess.pokeinfo.home.domain.repository.HomeRepository
import cl.mess.pokeinfo.home.domain.result.PokemonListResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val api: HomeService,
    private val mapper: HomeMapper
) : HomeRepository {

    override fun getPokemonList(): Flow<PagingData<PokemonListResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                HomePagingSource(
                    api = api,
                    mapper = mapper
                )
            }
        ).flow
            .map { pagingData ->
                pagingData.map { pokemon ->
                    PokemonListResult.Success(pokemonList = listOf(element = pokemon))
                }
            }
    }
}
