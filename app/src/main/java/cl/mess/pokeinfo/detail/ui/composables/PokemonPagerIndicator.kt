package cl.mess.pokeinfo.detail.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PokemonPagerIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(space = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(times = pageCount) { index ->
            Box(
                modifier = Modifier
                    .size(size = if (index == currentPage) 10.dp else 6.dp)
                    .clip(shape = CircleShape)
                    .background(
                        color = if (index == currentPage)
                            Color.Gray
                        else
                            Color.Gray.copy(alpha = 0.4f)
                    )
            )
        }
    }
}
