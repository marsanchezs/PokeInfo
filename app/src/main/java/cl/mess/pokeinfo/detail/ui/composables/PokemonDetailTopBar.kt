package cl.mess.pokeinfo.detail.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import cl.mess.pokeinfo.app.R
import cl.mess.pokeinfo.ui.composables.text.AttrsPokeInfoText
import cl.mess.pokeinfo.ui.composables.text.PokeInfoText
import cl.mess.pokeinfo.ui.composables.text.PoppinsFontStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailTopBar(
    navigateToHome: () -> Unit,
    onToggle: () -> Unit,
    isFavorite: Boolean,
    name: String,
    containerColor: Color = MaterialTheme.colorScheme.primary
) {
    TopAppBar(
        title = {
            PokeInfoText(
                attrs = AttrsPokeInfoText(
                    text = name,
                    fontSize = 30.sp,
                    pokeInfoFontStyle = PoppinsFontStyle.BOLD
                )
            )
        },
        navigationIcon = {
            IconButton(onClick = navigateToHome) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_outline_arrow_back_ios_24),
                    contentDescription = "Back",
                )
            }
        },
        actions = {
            IconButton(onClick = onToggle) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}
