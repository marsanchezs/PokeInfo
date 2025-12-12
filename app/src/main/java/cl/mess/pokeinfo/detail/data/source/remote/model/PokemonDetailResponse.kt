package cl.mess.pokeinfo.detail.data.source.remote.model

import cl.mess.pokeinfo.detail.data.source.remote.model.Constants.ABILITIES
import cl.mess.pokeinfo.detail.data.source.remote.model.Constants.ABILITY
import cl.mess.pokeinfo.detail.data.source.remote.model.Constants.BASE_EXPERIENCE
import cl.mess.pokeinfo.detail.data.source.remote.model.Constants.BASE_STAT
import cl.mess.pokeinfo.detail.data.source.remote.model.Constants.EFFORT
import cl.mess.pokeinfo.detail.data.source.remote.model.Constants.FRONT_DEFAULT
import cl.mess.pokeinfo.detail.data.source.remote.model.Constants.FRONT_SHINY
import cl.mess.pokeinfo.detail.data.source.remote.model.Constants.HEIGHT
import cl.mess.pokeinfo.detail.data.source.remote.model.Constants.ID
import cl.mess.pokeinfo.detail.data.source.remote.model.Constants.IS_HIDDEN
import cl.mess.pokeinfo.detail.data.source.remote.model.Constants.NAME
import cl.mess.pokeinfo.detail.data.source.remote.model.Constants.OFFICIAL_ARTWORK
import cl.mess.pokeinfo.detail.data.source.remote.model.Constants.OTHER
import cl.mess.pokeinfo.detail.data.source.remote.model.Constants.SLOT
import cl.mess.pokeinfo.detail.data.source.remote.model.Constants.SPRITES
import cl.mess.pokeinfo.detail.data.source.remote.model.Constants.STAT
import cl.mess.pokeinfo.detail.data.source.remote.model.Constants.STATS
import cl.mess.pokeinfo.detail.data.source.remote.model.Constants.TYPE
import cl.mess.pokeinfo.detail.data.source.remote.model.Constants.TYPES
import cl.mess.pokeinfo.detail.data.source.remote.model.Constants.URL
import cl.mess.pokeinfo.detail.data.source.remote.model.Constants.WEIGHT
import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    @SerializedName(ID) val id: Int?,
    @SerializedName(NAME) val name: String?,
    @SerializedName(HEIGHT) val height: Int?,
    @SerializedName(WEIGHT) val weight: Int?,
    @SerializedName(TYPES) val types: List<TypeSlotResponse?>?,
    @SerializedName(SPRITES) val sprites: PokemonSpritesResponse?,
    @SerializedName(STATS) val stats: List<StatResponse?>?,
    @SerializedName(ABILITIES) val abilities: List<AbilitySlotResponse?>?,
    @SerializedName(BASE_EXPERIENCE) val baseExperience: Int
)

data class TypeSlotResponse(
    @SerializedName(SLOT) val slot: Int?,
    @SerializedName(TYPE) val type: NamedApiResourceResponse?
)

data class StatResponse(
    @SerializedName(BASE_STAT) val baseStat: Int?,
    @SerializedName(EFFORT) val effort: Int?,
    @SerializedName(STAT) val stat: NamedApiResourceResponse?
)

data class AbilitySlotResponse(
    @SerializedName(IS_HIDDEN) val isHidden: Boolean?,
    @SerializedName(SLOT) val slot: Int?,
    @SerializedName(ABILITY) val ability: NamedApiResourceResponse?
)

data class PokemonSpritesResponse(
    @SerializedName(OTHER) val other: PokemonOtherSpritesResponse?
)

data class PokemonOtherSpritesResponse(
    @SerializedName(OFFICIAL_ARTWORK) val officialArtWork: OfficialArtworkResponse?
)

data class OfficialArtworkResponse(
    @SerializedName(FRONT_DEFAULT) val frontDefault: String?,
    @SerializedName(FRONT_SHINY) val frontShiny: String?
)

data class NamedApiResourceResponse(
    @SerializedName(NAME) val name: String?,
    @SerializedName(URL) val url: String?
)
