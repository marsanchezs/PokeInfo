package cl.mess.pokeinfo.home.ui.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import cl.mess.pokeinfo.home.domain.model.Pokemon
import cl.mess.pokeinfo.home.domain.result.PokemonListResult
import cl.mess.pokeinfo.ui.composables.template.PokeInfoEmpty
import cl.mess.pokeinfo.ui.composables.template.PokeInfoError

@Composable
fun PokemonGrid(
    pokemonPagingItems: LazyPagingItems<PokemonListResult>,
    onClick: (Pokemon) -> Unit
) {
    val isEmpty = pokemonPagingItems.itemCount == 0 &&
            pokemonPagingItems.loadState.refresh !is LoadState.Loading &&
            pokemonPagingItems.loadState.refresh !is LoadState.Error

    if (isEmpty) {
        PokeInfoEmpty()
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize().navigationBarsPadding()
        ) {
            items(pokemonPagingItems.itemCount) { index ->
                when (val item = pokemonPagingItems[index]) {
                    is PokemonListResult.Success -> {
                        item.pokemonList.forEach { pokemon ->
                            PokemonCard(
                                pokemon = pokemon,
                                onClick = { onClick(pokemon) }
                            )
                        }
                    }

                    null -> PokemonCardShimmer()
                    is PokemonListResult.Error -> {
                        Spacer(modifier = Modifier.height(0.dp))
                    }
                }
            }

            when (pokemonPagingItems.loadState.append) {
                is LoadState.Loading -> item { PokemonCardShimmer() }
                is LoadState.Error -> item { PokeInfoError(onRetry = { pokemonPagingItems.retry() }) }
                else -> {}
            }

            when (pokemonPagingItems.loadState.refresh) {
                is LoadState.Loading -> item { PokemonCardShimmer() }
                is LoadState.Error -> item { PokeInfoError(onRetry = { pokemonPagingItems.retry() }) }
                else -> {}
            }
        }
    }
}
