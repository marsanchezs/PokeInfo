package cl.mess.pokeinfo.home.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cl.mess.pokeinfo.home.domain.model.Pokemon
import coil.compose.AsyncImage

@Composable
fun PokemonCard(
    pokemon: Pokemon,
    onClick: (Pokemon) -> Unit
) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(all = 12.dp)
            .clickable(onClick = { onClick(pokemon) }),
        shape = RoundedCornerShape(size = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {

        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(all = 16.dp)
        ) {

            Text(
                text = "#${pokemon.number}",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.align(alignment = Alignment.TopEnd)
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                AsyncImage(
                    model = pokemon.imageUrl,
                    contentDescription = pokemon.name,
                    modifier = Modifier
                        .size(size = 100.dp)
                        .padding(top = 8.dp)
                )

                Spacer(modifier = Modifier.height(height = 12.dp))

                Text(
                    text = pokemon.name.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}
