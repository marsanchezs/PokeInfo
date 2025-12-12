package cl.mess.pokeinfo.home.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import cl.mess.pokeinfo.home.data.mapper.HomeMapper
import cl.mess.pokeinfo.home.data.source.remote.HomeService
import cl.mess.pokeinfo.home.domain.model.Pokemon

class HomePagingSource(
    private val api: HomeService,
    private val mapper: HomeMapper
) : PagingSource<Int, Pokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val currentPage = params.key ?: 0
            val response = api.getPokemonList(
                limit = 20,
                offset = currentPage * 20
            )

            if (response.isSuccessful) {
                val body = response.body()
                val results = body?.pokemonList ?: emptyList()

                val pokemonList = results.mapNotNull { pokemonResponse ->
                    runCatching { pokemonResponse?.let { mapper.toDomain(it) } }.getOrNull()
                }

                LoadResult.Page(
                    data = pokemonList,
                    prevKey = if (currentPage == 0) null else currentPage - 1,
                    nextKey = if (pokemonList.isEmpty()) null else currentPage + 1
                )
            } else {
                LoadResult.Error(Throwable("Error ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
