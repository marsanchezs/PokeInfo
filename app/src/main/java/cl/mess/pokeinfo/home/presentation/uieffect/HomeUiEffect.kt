package cl.mess.pokeinfo.home.presentation.uieffect

sealed class HomeUiEffect {
    data class NavigateToDetail(val id: Int) : HomeUiEffect()
    data object ShowModal : HomeUiEffect()
}
