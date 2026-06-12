package cl.mess.pokeinfo.ui.composables.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import cl.mess.pokeinfo.app.R

@Composable
fun PokeInfoText(attrs: AttrsPokeInfoText) {
    Text(
        text = attrs.text,
        modifier = attrs.modifier,
        color = attrs.color,
        fontSize = attrs.fontSize,
        fontStyle = attrs.fontStyle,
        fontWeight = attrs.fontWeight,
        fontFamily = FontFamily(
            Font(
                resId = attrs.pokeInfoFontStyle.resId,
                weight = attrs.pokeInfoFontStyle.weight
            )
        ),
        letterSpacing = attrs.letterSpacing,
        textDecoration = attrs.textDecoration,
        textAlign = attrs.textAlign,
        lineHeight = attrs.lineHeight,
        overflow = attrs.overflow,
        softWrap = attrs.softWrap,
        maxLines = attrs.maxLines,
        minLines = attrs.minLines
    )
}

@Composable
fun PokeInfoText(
    attrs: AttrsPokeInfoText,
    annotatedText: AnnotatedString
) {
    Text(
        text = annotatedText,
        modifier = attrs.modifier,
        color = attrs.color,
        fontSize = attrs.fontSize,
        fontStyle = attrs.fontStyle,
        fontWeight = attrs.fontWeight,
        fontFamily = attrs.pokeInfoFontStyle.fontFamily,
        letterSpacing = attrs.letterSpacing,
        textDecoration = attrs.textDecoration,
        textAlign = attrs.textAlign,
        lineHeight = attrs.lineHeight,
        overflow = attrs.overflow,
        softWrap = attrs.softWrap,
        maxLines = attrs.maxLines,
        minLines = attrs.minLines
    )
}

data class AttrsPokeInfoText(
    val modifier: Modifier = Modifier,
    val text: String = "",
    val textAlign: TextAlign? = null,
    val fontWeight: FontWeight? = null,
    val letterSpacing: TextUnit = TextUnit.Unspecified,
    val lineHeight: TextUnit = TextUnit.Unspecified,
    val textDecoration: TextDecoration = TextDecoration.None,
    val fontSize: TextUnit = TextUnit.Unspecified,
    val fontStyle: FontStyle? = null,
    val color: Color = Color.Unspecified,
    val overflow: TextOverflow = TextOverflow.Clip,
    val maxLines: Int = Int.MAX_VALUE,
    val minLines: Int = 1,
    val softWrap: Boolean = true,
    val pokeInfoFontStyle: PoppinsFontStyle = PoppinsFontStyle.REGULAR
)

enum class PoppinsFontStyle(
    val resId: Int,
    val weight: FontWeight
) {
    REGULAR(resId = R.font.poppins_regular, weight = FontWeight.Normal),
    MEDIUM(resId = R.font.poppins_medium, weight = FontWeight.Medium),
    BOLD(resId = R.font.poppins_bold, weight = FontWeight.Bold),
    EXTRA_LIGHT(resId = R.font.poppins_extra_light, weight = FontWeight.ExtraBold),
    LIGHT(resId = R.font.poppins_light, weight = FontWeight.ExtraBold)
}

val PoppinsFontStyle.fontFamily: FontFamily
    get() = FontFamily(Font(resId = this.resId, weight = this.weight))
