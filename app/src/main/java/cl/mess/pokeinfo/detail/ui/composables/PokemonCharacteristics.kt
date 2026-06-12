package cl.mess.pokeinfo.detail.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.mess.pokeinfo.app.R
import cl.mess.pokeinfo.detail.domain.model.PokemonAbility
import cl.mess.pokeinfo.ui.composables.text.AttrsPokeInfoText
import cl.mess.pokeinfo.ui.composables.text.PokeInfoText
import cl.mess.pokeinfo.ui.composables.text.PoppinsFontStyle

@Composable
fun PokemonCharacteristics(
    height: Int,
    weight: Int,
    abilities: List<PokemonAbility>
) {
    val fontColor = Color.Gray
    val fontSize = 14.sp
    val fontStyle = PoppinsFontStyle.REGULAR

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PokeInfoText(
            attrs = AttrsPokeInfoText(
                text = "About",
                fontSize = 20.sp,
                color = fontColor,
                pokeInfoFontStyle = PoppinsFontStyle.BOLD
            )
        )

        Spacer(modifier = Modifier.height(height = 4.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(intrinsicSize = IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PokemonCharacteristicsColumn(
                title = "Weight",
                fontColor = fontColor,
                modifier = Modifier.weight(weight = 1f),
                content = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(size = 24.dp),
                            painter = painterResource(id = R.drawable.ic_weight),
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        PokeInfoText(
                            attrs = AttrsPokeInfoText(
                                text = "$weight kg",
                                fontSize = fontSize,
                                color = fontColor,
                                pokeInfoFontStyle = fontStyle
                            )
                        )
                    }
                }
            )

            VerticalDivider(
                modifier = Modifier.padding(horizontal = 8.dp),
                thickness = 1.dp,
                color = fontColor
            )

            Box(
                modifier = Modifier.weight(weight = 1f),
                contentAlignment = Alignment.Center
            ) {
                PokemonCharacteristicsColumn(
                    title = "Height",
                    fontColor = fontColor,
                    content = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                modifier = Modifier.size(size = 24.dp),
                                painter = painterResource(id = R.drawable.ic_height),
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            PokeInfoText(
                                attrs = AttrsPokeInfoText(
                                    text = "$height m",
                                    fontSize = fontSize,
                                    color = fontColor,
                                    pokeInfoFontStyle = fontStyle
                                )
                            )
                        }
                    }
                )
            }

            VerticalDivider(
                modifier = Modifier.padding(horizontal = 8.dp),
                thickness = 1.dp,
                color = fontColor
            )

            PokemonCharacteristicsColumn(
                title = "Moves",
                fontColor = fontColor,
                modifier = Modifier
                    .wrapContentHeight()
                    .weight(weight = 1f),
                content = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        abilities.take(n = 2).forEach { ability ->
                            if ('-' in ability.name) {
                                val index = ability.name.indexOf('-')

                                val first = ability.name.take(index + 1)
                                val second = ability.name.substring(index + 1)

                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    PokeInfoText(
                                        attrs = AttrsPokeInfoText(
                                            text = first
                                                .replaceFirstChar { firstChar -> firstChar.uppercase() },
                                            fontSize = fontSize,
                                            color = fontColor,
                                            pokeInfoFontStyle = fontStyle,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis,
                                            textAlign = TextAlign.Center,
                                            lineHeight = 4.sp
                                        )
                                    )

                                    PokeInfoText(
                                        attrs = AttrsPokeInfoText(
                                            text = second
                                                .replaceFirstChar { firstChar -> firstChar.uppercase() },
                                            fontSize = fontSize,
                                            color = fontColor,
                                            pokeInfoFontStyle = fontStyle,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis,
                                            textAlign = TextAlign.Center
                                        )
                                    )
                                }
                            } else {
                                PokeInfoText(
                                    attrs = AttrsPokeInfoText(
                                        text = ability
                                            .name
                                            .replaceFirstChar { firstChar -> firstChar.uppercase() },
                                        fontSize = fontSize,
                                        color = fontColor,
                                        pokeInfoFontStyle = fontStyle,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                        textAlign = TextAlign.Center
                                    )
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}
