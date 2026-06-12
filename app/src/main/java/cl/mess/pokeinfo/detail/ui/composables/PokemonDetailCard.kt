package cl.mess.pokeinfo.detail.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cl.mess.pokeinfo.detail.domain.model.PokemonDetail

@Composable
fun PokemonDetailCard(
    pokemonDetail: PokemonDetail,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .padding(bottom = 8.dp)
            .clip(
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            )
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            PokemonType(
                types = pokemonDetail.types,
                backgroundColor = backgroundColor
            )

            PokemonCharacteristics(
                height = pokemonDetail.height,
                weight = pokemonDetail.weight,
                abilities = pokemonDetail.abilities
            )

            PokemonDescription(description = pokemonDetail.description)

            PokemonStats(
                stats = pokemonDetail.stats,
                backgroundColor = backgroundColor
            )
        }
    }
}
