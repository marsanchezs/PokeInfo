package cl.mess.pokeinfo.home.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import cl.mess.pokeinfo.home.ui.PokemonFilter

@Composable
fun ModalFilterContent(
    onAllClick: () -> Unit,
    onFavClick: () -> Unit,
    filter: PokemonFilter
) {
    Column {
        ModalFilterItem(
            onClick = onAllClick,
            imageVector = Icons.Default.Home,
            text = "All",
            isSelected = filter == PokemonFilter.All
        )

        ModalFilterItem(
            onClick = onFavClick,
            imageVector = Icons.Default.Favorite,
            text = "Favorites",
            isSelected = filter == PokemonFilter.Favorites
        )
    }
}
