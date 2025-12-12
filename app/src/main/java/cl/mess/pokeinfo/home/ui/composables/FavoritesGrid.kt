package cl.mess.pokeinfo.home.ui.composables

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import cl.mess.pokeinfo.home.domain.model.Pokemon
import cl.mess.pokeinfo.ui.composables.template.PokeInfoEmpty

@Composable
fun FavoritesGrid(
    favorites: List<Pokemon>,
    onPokemonClick: (Pokemon) -> Unit
) {
    if (favorites.isEmpty()) {
        PokeInfoEmpty()
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 2)
        ) {
            items(favorites) { pokemon ->
                PokemonCard(
                    pokemon = pokemon,
                    onClick = { onPokemonClick(pokemon) }
                )
            }
        }
    }
}
