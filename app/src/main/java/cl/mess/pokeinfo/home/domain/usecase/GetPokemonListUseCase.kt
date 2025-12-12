package cl.mess.pokeinfo.home.domain.usecase

import cl.mess.pokeinfo.home.domain.repository.HomeRepository
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    operator fun invoke() = repository.getPokemonList()
}
