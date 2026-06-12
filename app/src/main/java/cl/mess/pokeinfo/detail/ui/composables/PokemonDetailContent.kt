@file:Suppress("COMPOSE_APPLIER_CALL_MISMATCH")

package cl.mess.pokeinfo.detail.ui.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.mess.pokeinfo.detail.domain.model.PokemonDetail
import cl.mess.pokeinfo.utils.Utils.getColor

@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailContent(
    navigateToHome: () -> Unit,
    onToggle: () -> Unit,
    isFavorite: Boolean,
    pokemonDetail: PokemonDetail
) {
    val primaryType = pokemonDetail.types
        .firstOrNull { it.slot == 1 }
        ?.name
        ?: "normal"

    val backgroundColor = getColor(type = primaryType)
    val imageSize = 300.dp

    val images = listOf(
        pokemonDetail.images.frontDefault,
        pokemonDetail.images.frontShiny
    )

    val pagerState = rememberPagerState(
        pageCount = { images.size }
    )

    Scaffold(
        topBar = {
            PokemonDetailTopBar(
                navigateToHome = navigateToHome,
                onToggle = onToggle,
                isFavorite = isFavorite,
                name = pokemonDetail.name.replaceFirstChar { it.uppercase() },
                containerColor = backgroundColor
            )
        }
    ) { paddingValues ->

        BoxWithConstraints(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
                .background(color = backgroundColor)
        ) {
            val splitPosition = maxHeight * 0.2f

            Column(modifier = Modifier.fillMaxSize()) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(weight = 0.2f)
                )

                PokemonDetailCard(
                    pokemonDetail = pokemonDetail,
                    backgroundColor = backgroundColor,
                    modifier = Modifier.weight(weight = 0.8f)
                )
            }

            PokemonPager(
                pagerState = pagerState,
                splitPosition = splitPosition,
                imageSize = imageSize,
                images = images
            )
        }
    }
}
