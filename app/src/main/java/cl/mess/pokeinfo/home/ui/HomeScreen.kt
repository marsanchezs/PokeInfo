package cl.mess.pokeinfo.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import cl.mess.pokeinfo.home.presentation.HomeViewModel
import cl.mess.pokeinfo.home.presentation.uieffect.HomeUiEffect
import cl.mess.pokeinfo.home.ui.composables.FavoritesGrid
import cl.mess.pokeinfo.home.ui.composables.ModalFilterContent
import cl.mess.pokeinfo.home.ui.composables.PokemonGrid
import cl.mess.pokeinfo.ui.composables.loading.PokeInfoLoading
import cl.mess.pokeinfo.ui.composables.modal.PokeInfoBottomSheetModal
import cl.mess.pokeinfo.ui.composables.template.PokeInfoError

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val pokemonPagingItems =
        viewModel.pokemonPagingData.collectAsLazyPagingItems()

    var showModalFilter by remember { mutableStateOf(value = false) }
    val favorites by viewModel.favorites.collectAsState()
    val filter by viewModel.filter.collectAsState()

    LaunchedEffect(Unit) {
        snapshotFlow { filter }
            .collect { currentFilter ->
                if (currentFilter == PokemonFilter.Favorites) {
                    viewModel.getFavorites()
                }
            }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEffects.collect { effect ->
            when (effect) {
                is HomeUiEffect.NavigateToDetail -> {
                    navController.navigate("detail/${effect.id}")
                }

                HomeUiEffect.ShowModal -> showModalFilter = true
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (filter) {
            PokemonFilter.All -> {
                PokemonGrid(
                    pokemonPagingItems = pokemonPagingItems,
                    onClick = { pokemon -> viewModel.navigateToDetail(id = pokemon.id) }
                )
            }

            PokemonFilter.Favorites -> {
                FavoritesGrid(
                    favorites = favorites,
                    onPokemonClick = { pokemon ->
                        viewModel.navigateToDetail(id = pokemon.id)
                    }
                )
            }
        }

        FloatingActionButton(
            onClick = { viewModel.showModal() },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
        ) {
            Icon(
                imageVector = when (filter) {
                    PokemonFilter.All -> Icons.Default.Home
                    PokemonFilter.Favorites -> Icons.Default.Favorite
                },
                contentDescription = "Filter"
            )
        }

        if (pokemonPagingItems.loadState.refresh is LoadState.Loading) {
            PokeInfoLoading()
        }

        if (pokemonPagingItems.loadState.refresh is LoadState.Error) {
            PokeInfoError(
                onRetry = { pokemonPagingItems.retry() }
            )
        }

        if (showModalFilter) {
            PokeInfoBottomSheetModal(
                onDismiss = { showModalFilter = false }
            ) {
                ModalFilterContent(
                    onAllClick = {
                        viewModel.setFilter(PokemonFilter.All)
                        showModalFilter = false
                    },
                    onFavClick = {
                        viewModel.setFilter(PokemonFilter.Favorites)
                        viewModel.getFavorites()
                        showModalFilter = false
                    },
                    filter = filter
                )
            }
        }
    }
}
