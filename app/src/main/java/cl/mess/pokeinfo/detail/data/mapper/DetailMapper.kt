package cl.mess.pokeinfo.detail.data.mapper

import cl.mess.pokeinfo.detail.data.source.remote.model.PokemonDetailResponse
import cl.mess.pokeinfo.detail.data.source.remote.model.PokemonSpeciesResponse
import cl.mess.pokeinfo.detail.domain.model.PokemonAbility
import cl.mess.pokeinfo.detail.domain.model.PokemonDetail
import cl.mess.pokeinfo.detail.domain.model.PokemonImages
import cl.mess.pokeinfo.detail.domain.model.PokemonStat
import cl.mess.pokeinfo.detail.domain.model.PokemonType
import javax.inject.Inject

class DetailMapper @Inject constructor() {

    fun toDomain(
        detail: PokemonDetailResponse,
        species: PokemonSpeciesResponse
    ): PokemonDetail {

        requireNotNull(detail.id) { "Pokemon id is null" }
        require(!detail.name.isNullOrBlank()) { "Pokemon name is null or blank" }
        requireNotNull(detail.height) { "Pokemon height is null" }
        requireNotNull(detail.weight) { "Pokemon weight is null" }
        requireNotNull(detail.sprites?.other?.officialArtWork?.frontDefault) { "Front image is null" }
        requireNotNull(detail.sprites.other.officialArtWork.frontShiny) { "Front shiny image is null" }
        requireNotNull(detail.types) { "Types list is null" }
        requireNotNull(detail.stats) { "Stats list is null" }
        requireNotNull(detail.abilities) { "Abilities list is null" }

        val description = species.flavorTextEntries
            ?.firstOrNull { entry ->
                entry?.language?.name == "en" &&
                        !entry.flavorText.isNullOrBlank()
            }
            ?.flavorText
            ?.replace("\n", " ")
            ?.replace("\u000c", " ")
            ?.trim()

        require(!description.isNullOrBlank()) {
            "Pokemon description not found"
        }

        return PokemonDetail(
            id = detail.id,
            name = detail.name,
            description = description,
            height = detail.height,
            weight = detail.weight,
            baseExperience = detail.baseExperience,
            images = PokemonImages(
                frontDefault = detail.sprites.other.officialArtWork.frontDefault,
                frontShiny = detail.sprites.other.officialArtWork.frontShiny
            ),
            types = detail.types.map { typeSlot ->
                requireNotNull(typeSlot?.type?.name) { "Type name is null" }
                PokemonType(
                    slot = typeSlot.slot ?: 0,
                    name = typeSlot.type.name
                )
            },
            stats = detail.stats.map { statSlot ->
                requireNotNull(statSlot?.stat?.name) { "Stat name is null" }
                PokemonStat(
                    baseStat = statSlot.baseStat ?: 0,
                    effort = statSlot.effort ?: 0,
                    name = statSlot.stat.name
                )
            },
            abilities = detail.abilities.map { abilitySlot ->
                requireNotNull(abilitySlot?.ability?.name) { "Ability name is null" }
                PokemonAbility(
                    isHidden = abilitySlot.isHidden ?: false,
                    slot = abilitySlot.slot ?: 0,
                    name = abilitySlot.ability.name
                )
            }
        )
    }
}
