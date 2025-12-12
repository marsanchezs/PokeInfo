package cl.mess.pokeinfo.home.data.source.remote

import cl.mess.pokeinfo.home.data.source.remote.model.Constants.LIMIT
import cl.mess.pokeinfo.home.data.source.remote.model.Constants.OFFSET
import cl.mess.pokeinfo.home.data.source.remote.model.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query(OFFSET) offset: Int,
        @Query(LIMIT) limit: Int
    ): Response<PokemonListResponse>
}
