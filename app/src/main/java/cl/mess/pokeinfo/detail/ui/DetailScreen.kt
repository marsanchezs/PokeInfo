package cl.mess.pokeinfo.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cl.mess.pokeinfo.detail.domain.model.PokemonDetail
import cl.mess.pokeinfo.detail.presentation.DetailViewModel
import cl.mess.pokeinfo.detail.presentation.uistate.DetailUiState
import cl.mess.pokeinfo.detail.ui.composables.PokemonDetailTopBar
import cl.mess.pokeinfo.ui.composables.loading.PokeInfoLoading
import cl.mess.pokeinfo.ui.composables.template.PokeInfoError
import coil.compose.AsyncImage

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailContent(
    navigateToHome: () -> Unit,
    onToggle: () -> Unit,
    isFavorite: Boolean,
    pokemonDetail: PokemonDetail
) {
    Scaffold(
        topBar = {
            PokemonDetailTopBar(
                navigateToHome = navigateToHome,
                onToggle = onToggle,
                isFavorite = isFavorite,
                name = pokemonDetail.name.replaceFirstChar { it.uppercase() },
            )
        }
    ) { paddingValues ->

        val images = listOf(
            pokemonDetail.images.frontDefault,
            pokemonDetail.images.frontShiny
        )

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Carrusel de imágenes
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(images) { url ->
                    AsyncImage(
                        model = url,
                        contentDescription = pokemonDetail.name,
                        modifier = Modifier
                            .size(200.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                }
            }

            // Nombre
            Text(
                text = pokemonDetail.name.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                pokemonDetail.types.forEach { type ->
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color.LightGray,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = type.name.replaceFirstChar { it.uppercase() },
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black
                        )
                    }
                }
            }


            // Habilidades
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                Text(
                    text = "Abilities",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                pokemonDetail.abilities.forEach { ability ->
                    Text(
                        text = "- ${ability.name.replaceFirstChar { it.uppercase() }}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
