package cl.mess.pokeinfo.detail.domain.usecase

import cl.mess.pokeinfo.detail.domain.repository.DetailRepository
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val repository: DetailRepository
) {
    suspend operator fun invoke(id: Int) = repository.getPokemonDetail(id = id)
}
