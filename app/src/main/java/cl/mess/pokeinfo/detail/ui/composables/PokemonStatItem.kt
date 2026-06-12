package cl.mess.pokeinfo.detail.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.mess.pokeinfo.detail.domain.model.PokemonStat
import cl.mess.pokeinfo.ui.composables.text.AttrsPokeInfoText
import cl.mess.pokeinfo.ui.composables.text.PokeInfoText
import cl.mess.pokeinfo.ui.composables.text.PoppinsFontStyle

@Composable
fun PokemonStatItem(
    stat: PokemonStat,
    backgroundColor: Color
) {
    val maxStat = 100f
    val progress = (stat.baseStat / maxStat).coerceIn(
        minimumValue = 0f,
        maximumValue = 1f
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {

        PokeInfoText(
            attrs = AttrsPokeInfoText(
                modifier = Modifier.width(width = 50.dp),
                text = getStatName(name = stat.name),
                fontSize = 16.sp,
                color = Color.Gray.copy(alpha = 0.5f),
                pokeInfoFontStyle = PoppinsFontStyle.BOLD
            )
        )

        VerticalDivider(
            modifier = Modifier.padding(horizontal = 8.dp),
            thickness = 1.dp,
            color = Color.Gray
        )

        PokeInfoText(
            attrs = AttrsPokeInfoText(
                modifier = Modifier.width(width = 40.dp),
                text = stat.baseStat.toString(),
                fontSize = 16.sp,
                color = Color.Gray
            )
        )

        Spacer(modifier = Modifier.width(width = 4.dp))

        Box(
            modifier = Modifier
                .weight(weight = 1f)
                .height(height = 12.dp)
                .clip(shape = RoundedCornerShape(size = 4.dp))
                .background(
                    color = if (progress < 1f)
                        backgroundColor.copy(alpha = 0.3f) else Color.Transparent
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(fraction = progress)
                    .background(color = backgroundColor)
            )
        }
    }
}

fun getStatName(name: String) =
    when (name) {
        "hp" -> "HP"
        "attack" -> "ATK"
        "defense" -> "DEF"
        "special-attack" -> "SATK"
        "special-defense" -> "SDEF"
        "speed" -> "SPD"
        else -> ""
    }
