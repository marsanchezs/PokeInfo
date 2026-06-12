package cl.mess.pokeinfo.detail.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.mess.pokeinfo.detail.domain.model.PokemonType
import cl.mess.pokeinfo.ui.composables.text.AttrsPokeInfoText
import cl.mess.pokeinfo.ui.composables.text.PokeInfoText
import cl.mess.pokeinfo.ui.composables.text.PoppinsFontStyle
import kotlin.collections.forEach

@Composable
fun PokemonType(
    types: List<PokemonType>,
    backgroundColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 160.dp),
        horizontalArrangement = Arrangement.spacedBy(
            space = 8.dp,
            alignment = Alignment.CenterHorizontally
        )
    ) {
        types.forEach { type ->
            Box(
                modifier = Modifier
                    .background(
                        color = backgroundColor,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                PokeInfoText(
                    attrs = AttrsPokeInfoText(
                        text = type.name.replaceFirstChar { it.uppercase() },
                        fontSize = 14.sp,
                        color = Color.White,
                        pokeInfoFontStyle = PoppinsFontStyle.MEDIUM
                    )
                )
            }
        }
    }
}
