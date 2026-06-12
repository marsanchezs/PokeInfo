package cl.mess.pokeinfo.detail.domain.model

data class PokemonDetail(
    val id: Int,
    val description: String,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<PokemonType>,
    val images: PokemonImages,
    val stats: List<PokemonStat>,
    val abilities: List<PokemonAbility>,
    val baseExperience: Int
)

data class PokemonType(
    val slot: Int,
    val name: String
)

data class PokemonStat(
    val baseStat: Int,
    val effort: Int,
    val name: String
)

data class PokemonAbility(
    val isHidden: Boolean,
    val slot: Int,
    val name: String
)

data class PokemonImages(
    val frontDefault: String,
    val frontShiny: String
)
