package cl.mess.pokeinfo.common.room.data.mapper

import cl.mess.pokeinfo.common.room.data.source.local.entities.Favorite
import cl.mess.pokeinfo.detail.domain.model.PokemonDetail
import cl.mess.pokeinfo.detail.domain.model.PokemonImages
import org.junit.Test
import kotlin.test.assertEquals

class FavoritesMapperTest {

    private val mapper = FavoritesMapper()

    @Test
    fun `PokemonDetail to Favorite mapping`() {
        val detail = PokemonDetail(
            id = 1,
            name = "Pikachu",
            height = 4,
            weight = 60,
            baseExperience = 112,
            images = PokemonImages(frontDefault = "front.png", frontShiny = "shiny.png"),
            types = emptyList(),
            stats = emptyList(),
            abilities = emptyList(),
            description = "description"
        )

        val favorite = with(mapper) { detail.toFavorite() }

        assertEquals(detail.id, favorite.id)
        assertEquals(detail.id, favorite.number)
        assertEquals(detail.name, favorite.name)
        assertEquals(detail.images.frontDefault, favorite.imageUrl)
    }

    @Test
    fun `Favorite to Pokemon mapping`() {
        val favorite = Favorite(
            id = 1,
            number = 1,
            name = "Pikachu",
            imageUrl = "front.png"
        )

        val pokemon = with(mapper) { favorite.toPokemon() }

        assertEquals(favorite.id, pokemon.id)
        assertEquals(favorite.number, pokemon.number)
        assertEquals(favorite.name, pokemon.name)
        assertEquals(favorite.imageUrl, pokemon.imageUrl)
    }

    @Test
    fun `List of Favorite to List of Pokemon`() {
        val favorites = listOf(
            Favorite(id = 1, number = 1, name = "Pikachu", imageUrl = "front.png"),
            Favorite(id = 2, number = 2, name = "Bulbasaur", imageUrl = "bulba.png")
        )

        val pokemonList = mapper.toPokemonList(favorites)

        assertEquals(favorites.size, pokemonList.size)
        assertEquals(favorites[0].name, pokemonList[0].name)
        assertEquals(favorites[1].imageUrl, pokemonList[1].imageUrl)
    }
}
