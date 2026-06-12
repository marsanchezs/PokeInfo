package cl.mess.pokeinfo.utils

import androidx.compose.ui.graphics.Color

object Utils {
    fun getColor(type: String) = when (type.lowercase()) {
        "fire" -> Color(color = 0xFFFF7043)
        "water" -> Color(color = 0xFF42A5F5)
        "grass" -> Color(color = 0xFF66BB6A)
        "electric" -> Color(color = 0xFFFFEB3B)
        "ice" -> Color(color = 0xFF80DEEA)
        "fighting" -> Color(color = 0xFF8D6E63)
        "poison" -> Color(color = 0xFFBA68C8)
        "ground" -> Color(color = 0xFFD4A373)
        "flying" -> Color(color = 0xFF90CAF9)
        "psychic" -> Color(color = 0xFFF06292)
        "bug" -> Color(color = 0xFF9CCC65)
        "rock" -> Color(color = 0xFFBCAAA4)
        "ghost" -> Color(color = 0xFF7E57C2)
        "dragon" -> Color(color = 0xFF5C6BC0)
        "dark" -> Color(color = 0xFF616161)
        "steel" -> Color(color = 0xFFB0BEC5)
        "fairy" -> Color(color = 0xFFF48FB1)
        else -> Color.LightGray
    }
}
