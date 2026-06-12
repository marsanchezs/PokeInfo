package cl.mess.pokeinfo.detail.data.source.remote.model

import cl.mess.pokeinfo.detail.data.source.remote.model.Constants.FLAVOR_TEXT
import cl.mess.pokeinfo.detail.data.source.remote.model.Constants.FLAVOR_TEXT_ENTRIES
import cl.mess.pokeinfo.detail.data.source.remote.model.Constants.LANGUAGE
import com.google.gson.annotations.SerializedName

data class PokemonSpeciesResponse(
    @SerializedName(value = FLAVOR_TEXT_ENTRIES) val flavorTextEntries: List<FlavorTextEntryResponse?>?
)

data class FlavorTextEntryResponse(
    @SerializedName(value = FLAVOR_TEXT) val flavorText: String?,
    @SerializedName(value = LANGUAGE) val language: NamedApiResourceResponse?
)
