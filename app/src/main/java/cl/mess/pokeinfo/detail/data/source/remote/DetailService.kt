package cl.mess.pokeinfo.detail.data.source.remote

import cl.mess.pokeinfo.detail.data.source.remote.model.Constants.ID
import cl.mess.pokeinfo.detail.data.source.remote.model.PokemonDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailService {

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path(ID) id: Int): Response<PokemonDetailResponse>
}
