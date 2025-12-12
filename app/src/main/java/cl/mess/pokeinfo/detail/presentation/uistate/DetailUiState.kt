package cl.mess.pokeinfo.detail.presentation.uistate

import cl.mess.pokeinfo.detail.domain.model.PokemonDetail

sealed class DetailUiState {
    data object Loading : DetailUiState()
    data class Success(val pokemonDetail: PokemonDetail) : DetailUiState()
    data object Error : DetailUiState()
}
