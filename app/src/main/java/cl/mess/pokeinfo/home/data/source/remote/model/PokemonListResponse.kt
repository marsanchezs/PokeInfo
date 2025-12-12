package cl.mess.pokeinfo.home.data.source.remote.model

import cl.mess.pokeinfo.home.data.source.remote.model.Constants.COUNT
import cl.mess.pokeinfo.home.data.source.remote.model.Constants.NAME
import cl.mess.pokeinfo.home.data.source.remote.model.Constants.NEXT
import cl.mess.pokeinfo.home.data.source.remote.model.Constants.PREVIOUS
import cl.mess.pokeinfo.home.data.source.remote.model.Constants.RESULTS
import cl.mess.pokeinfo.home.data.source.remote.model.Constants.URL
import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    @SerializedName(COUNT) val count: Int?,
    @SerializedName(NEXT) val next: String?,
    @SerializedName(PREVIOUS) val previous: String?,
    @SerializedName(RESULTS) val pokemonList: List<PokemonResponse?>?
)

data class PokemonResponse(
    @SerializedName(NAME) val name: String?,
    @SerializedName(URL) val url: String?
)
