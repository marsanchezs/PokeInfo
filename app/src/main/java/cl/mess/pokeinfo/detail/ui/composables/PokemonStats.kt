package cl.mess.pokeinfo.detail.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.mess.pokeinfo.detail.domain.model.PokemonStat
import cl.mess.pokeinfo.ui.composables.text.AttrsPokeInfoText
import cl.mess.pokeinfo.ui.composables.text.PokeInfoText
import cl.mess.pokeinfo.ui.composables.text.PoppinsFontStyle
import kotlin.collections.forEach

@Composable
fun PokemonStats(
    stats: List<PokemonStat>,
    backgroundColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PokeInfoText(
            attrs = AttrsPokeInfoText(
                text = "Basic Stats",
                fontSize = 20.sp,
                color = Color.Gray,
                pokeInfoFontStyle = PoppinsFontStyle.BOLD
            )
        )

        Spacer(modifier = Modifier.height(height = 4.dp))

        stats.forEach { stat ->
            PokemonStatItem(
                stat = stat,
                backgroundColor = backgroundColor
            )
        }
    }
}
