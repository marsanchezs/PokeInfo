package cl.mess.pokeinfo.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import cl.mess.pokeinfo.common.room.domain.usecase.GetAllFavoritesUseCase
import cl.mess.pokeinfo.home.domain.model.Pokemon
import cl.mess.pokeinfo.home.domain.result.PokemonListResult
import cl.mess.pokeinfo.home.domain.usecase.GetPokemonListUseCase
import cl.mess.pokeinfo.home.presentation.uieffect.HomeUiEffect
import cl.mess.pokeinfo.home.ui.PokemonFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getPokemonListUseCase: GetPokemonListUseCase,
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase
) : ViewModel() {
    private val uiEffect = MutableSharedFlow<HomeUiEffect>()

    val pokemonPagingData: Flow<PagingData<PokemonListResult>> =
        getPokemonListUseCase()
            .cachedIn(scope = viewModelScope)

    val favorites = MutableStateFlow<List<Pokemon>>(emptyList())

    private val _filter = MutableStateFlow(PokemonFilter.All)
    val filter: StateFlow<PokemonFilter> = _filter.asStateFlow()

    fun setFilter(filter: PokemonFilter) {
        _filter.value = filter
    }

    fun getFavorites() {
        viewModelScope.launch {
            favorites.value = getAllFavoritesUseCase()
        }
    }

    fun showModal() {
        viewModelScope.launch {
            uiEffect.emit(HomeUiEffect.ShowModal)
        }
    }

    fun navigateToDetail(id: Int) {
        viewModelScope.launch {
            uiEffect.emit(HomeUiEffect.NavigateToDetail(id))
        }
    }

    val uiEffects: SharedFlow<HomeUiEffect> = uiEffect.asSharedFlow()
}
