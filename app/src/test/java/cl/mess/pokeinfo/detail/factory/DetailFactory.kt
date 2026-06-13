package cl.mess.pokeinfo.detail.factory

import cl.mess.pokeinfo.detail.data.source.remote.model.AbilitySlotResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.FlavorTextEntryResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.NamedApiResourceResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.OfficialArtworkResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.PokemonDetailResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.PokemonOtherSpritesResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.PokemonSpeciesResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.PokemonSpritesResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.StatResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.TypeSlotResponse
import cl.mess.pokeinfo.utils.RandomFactory

const val EMPTY_STRING = ""

object DetailFactory {

    fun makePokemonDetailResponse(
        id: String = EMPTY_STRING,
        name: String = EMPTY_STRING,
        frontDefault: String = EMPTY_STRING
    ) = PokemonDetailResponse(
        id = if (id.isEmpty()) RandomFactory.generateRandomInt() else id.toInt(),
        name = name.ifEmpty { RandomFactory.generateRandomString() },
        height = 4,
        weight = 60,
        baseExperience = 112,
        sprites = PokemonSpritesResponse(
            other = PokemonOtherSpritesResponse(
                officialArtWork = OfficialArtworkResponse(
                    frontDefault = frontDefault.ifEmpty { RandomFactory.generateRandomString() },
                    frontShiny = "front_shiny.png"
                )
            )
        ),
        types = listOf(
            TypeSlotResponse(
                slot = 1,
                type = NamedApiResourceResponse(name = "electric", url = "url")
            )
        ),
        stats = listOf(
            StatResponse(
                baseStat = 35,
                effort = 0,
                stat = NamedApiResourceResponse(name = "speed", url = "url")
            )
        ),
        abilities = listOf(
            AbilitySlotResponse(
                isHidden = false,
                slot = 1,
                ability = NamedApiResourceResponse(name = "static", url = "url")
            )
        )
    )

    fun makePokemonSpeciesResponse() = PokemonSpeciesResponse(
        flavorTextEntries = listOf(
            FlavorTextEntryResponse(
                flavorText = "A mouse Pokémon",
                language = NamedApiResourceResponse(
                    name = "en",
                    url = "url"
                )
            )
        )
    )
}
