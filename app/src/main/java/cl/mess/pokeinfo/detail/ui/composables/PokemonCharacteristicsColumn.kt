package cl.mess.pokeinfo.detail.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import cl.mess.pokeinfo.ui.composables.text.AttrsPokeInfoText
import cl.mess.pokeinfo.ui.composables.text.PokeInfoText
import cl.mess.pokeinfo.ui.composables.text.PoppinsFontStyle

@Composable
fun PokemonCharacteristicsColumn(
    title: String,
    fontColor: Color,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.weight(weight = 1f),
                contentAlignment = Alignment.Center
            ) {
                content()
            }

            PokeInfoText(
                attrs = AttrsPokeInfoText(
                    text = title,
                    fontSize = 12.sp,
                    color = fontColor,
                    pokeInfoFontStyle = PoppinsFontStyle.LIGHT
                )
            )
        }
    }
}
