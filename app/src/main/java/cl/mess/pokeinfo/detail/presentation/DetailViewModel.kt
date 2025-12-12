package cl.mess.pokeinfo.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.mess.pokeinfo.common.room.domain.usecase.AddFavoriteUseCase
import cl.mess.pokeinfo.common.room.domain.usecase.DeleteFavoriteUseCase
import cl.mess.pokeinfo.common.room.domain.usecase.IsFavoriteUseCase
import cl.mess.pokeinfo.detail.domain.model.PokemonDetail
import cl.mess.pokeinfo.detail.domain.result.PokemonDetailResult
import cl.mess.pokeinfo.detail.domain.usecase.GetPokemonDetailUseCase
import cl.mess.pokeinfo.detail.presentation.uistate.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val addUseCase: AddFavoriteUseCase,
    private val deleteUseCase: DeleteFavoriteUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase
) : ViewModel() {
    private val uiState = MutableStateFlow<DetailUiState>(value = DetailUiState.Loading)
    val isFavorite = MutableStateFlow(value = false)

    fun getPokemonDetail(id: Int) {
        viewModelScope.launch {
            uiState.value = DetailUiState.Loading

            getPokemonDetailUseCase(id = id).collect { result ->
                when (result) {
                    is PokemonDetailResult.Success ->
                        uiState.value = DetailUiState.Success(pokemonDetail = result.pokemonDetail)

                    PokemonDetailResult.Error -> uiState.value = DetailUiState.Error
                }
            }
        }
    }

    fun checkIfPokemonIsFavorite(name: String) {
        viewModelScope.launch {
            val exists = isFavoriteUseCase.execute(pokemonName = name)
            isFavorite.value = exists
        }
    }

    fun toggleFavorite(pokemon: PokemonDetail) {
        viewModelScope.launch {
            val favorite = isFavorite.value
            if (favorite)
                deleteUseCase(pokemon = pokemon)
            else
                addUseCase(pokemon = pokemon)

            isFavorite.value = !favorite
        }
    }

    fun uiStates(): StateFlow<DetailUiState> = uiState.asStateFlow()
}
