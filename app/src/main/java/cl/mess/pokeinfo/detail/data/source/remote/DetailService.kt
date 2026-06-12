package cl.mess.pokeinfo.detail.data.source.remote

import cl.mess.pokeinfo.detail.data.source.remote.model.Constants.ID
import cl.mess.pokeinfo.detail.data.source.remote.model.PokemonDetailResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.PokemonSpeciesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailService {

    @GET(value = "pokemon/{id}")
    suspend fun getPokemonDetail(@Path(value = ID) id: Int): Response<PokemonDetailResponse>

    @GET(value = "pokemon-species/{id}")
    suspend fun getPokemonSpecies(@Path(value = ID) id: Int): Response<PokemonSpeciesResponse>
}
