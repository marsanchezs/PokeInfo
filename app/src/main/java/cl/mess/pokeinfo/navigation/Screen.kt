package cl.mess.pokeinfo.navigation

import cl.mess.pokeinfo.navigation.Constants.DETAIL
import cl.mess.pokeinfo.navigation.Constants.HOME

sealed class Screen(val route: String) {
    data object Detail : Screen(route = DETAIL)
    data object Home : Screen(route = HOME)
}
