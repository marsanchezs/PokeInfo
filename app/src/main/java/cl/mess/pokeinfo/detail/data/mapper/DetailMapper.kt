package cl.mess.pokeinfo.detail.data.mapper

import cl.mess.pokeinfo.detail.data.source.remote.model.PokemonDetailResponse
import cl.mess.pokeinfo.detail.domain.model.PokemonAbility
import cl.mess.pokeinfo.detail.domain.model.PokemonDetail
import cl.mess.pokeinfo.detail.domain.model.PokemonImages
import cl.mess.pokeinfo.detail.domain.model.PokemonStat
import cl.mess.pokeinfo.detail.domain.model.PokemonType
import javax.inject.Inject

class DetailMapper @Inject constructor() {

    fun toDomain(response: PokemonDetailResponse): PokemonDetail {
        requireNotNull(response.id) { "Pokemon id is null" }
        require(!response.name.isNullOrBlank()) { "Pokemon name is null or blank" }
        requireNotNull(response.height) { "Pokemon height is null" }
        requireNotNull(response.weight) { "Pokemon weight is null" }
        requireNotNull(response.sprites?.other?.officialArtWork?.frontDefault) { "Front image is null" }
        requireNotNull(response.sprites.other.officialArtWork.frontShiny) { "Front shiny image is null" }
        requireNotNull(response.types) { "Types list is null" }
        requireNotNull(response.stats) { "Stats list is null" }
        requireNotNull(response.abilities) { "Abilities list is null" }

        return PokemonDetail(
            id = response.id,
            name = response.name,
            height = response.height,
            weight = response.weight,
            baseExperience = response.baseExperience,
            images = PokemonImages(
                frontDefault = response.sprites.other.officialArtWork.frontDefault,
                frontShiny = response.sprites.other.officialArtWork.frontShiny
            ),
            types = response.types.map { typeSlot ->
                requireNotNull(typeSlot?.type?.name) { "Type name is null" }
                PokemonType(
                    slot = typeSlot.slot ?: 0,
                    name = typeSlot.type.name
                )
            },
            stats = response.stats.map { statSlot ->
                requireNotNull(statSlot?.stat?.name) { "Stat name is null" }
                PokemonStat(
                    baseStat = statSlot.baseStat ?: 0,
                    effort = statSlot.effort ?: 0,
                    name = statSlot.stat.name
                )
            },
            abilities = response.abilities.map { abilitySlot ->
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
