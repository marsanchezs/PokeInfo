package cl.mess.pokeinfo.detail.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import cl.mess.pokeinfo.detail.presentation.DetailViewModel
import cl.mess.pokeinfo.detail.presentation.uistate.DetailUiState
import cl.mess.pokeinfo.detail.ui.composables.PokemonDetailContent
import cl.mess.pokeinfo.ui.composables.loading.PokeInfoLoading
import cl.mess.pokeinfo.ui.composables.template.PokeInfoError

@Composable
fun DetailScreen(
    navigateToHome: () -> Unit,
    id: Int,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiStates().collectAsState()
    val isFavorite by viewModel.isFavorite.collectAsState()
    var name by remember { mutableStateOf(value = "") }

    LaunchedEffect(key1 = Unit) {
        viewModel.getPokemonDetail(id = id)
    }

    LaunchedEffect(name) {
        viewModel.checkIfPokemonIsFavorite(name = name)
    }

    when (uiState) {

        is DetailUiState.Loading -> PokeInfoLoading()

        is DetailUiState.Error -> PokeInfoError(onRetry = { viewModel.getPokemonDetail(id = id) })

        is DetailUiState.Success -> {
            val pokemonDetail = (uiState as DetailUiState.Success).pokemonDetail
            name = pokemonDetail.name

            PokemonDetailContent(
                navigateToHome = navigateToHome,
                onToggle = { viewModel.toggleFavorite(pokemon = pokemonDetail) },
                isFavorite = isFavorite,
                pokemonDetail = pokemonDetail
            )
        }
    }
}
