package cl.mess.pokeinfo.detail.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.mess.pokeinfo.ui.composables.text.AttrsPokeInfoText
import cl.mess.pokeinfo.ui.composables.text.PokeInfoText
import cl.mess.pokeinfo.ui.composables.text.PoppinsFontStyle

@Composable
fun PokemonDescription(description: String) {
    PokeInfoText(
        attrs = AttrsPokeInfoText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            text = description,
            fontSize = 14.sp,
            color = Color.Gray,
            pokeInfoFontStyle = PoppinsFontStyle.LIGHT
        )
    )
}
