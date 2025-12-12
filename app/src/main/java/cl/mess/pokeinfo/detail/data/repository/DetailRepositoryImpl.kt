package cl.mess.pokeinfo.detail.data.repository

import cl.mess.pokeinfo.detail.data.mapper.DetailMapper
import cl.mess.pokeinfo.detail.data.source.remote.DetailService
import cl.mess.pokeinfo.detail.domain.repository.DetailRepository
import cl.mess.pokeinfo.detail.domain.result.PokemonDetailResult
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val api: DetailService,
    private val mapper: DetailMapper
) : DetailRepository {

    override suspend fun getPokemonDetail(id: Int) = flow {

        try {
            val response = api.getPokemonDetail(id = id)

            if (!response.isSuccessful) {
                emit(value = PokemonDetailResult.Error)
                return@flow
            }

            val pokemonDetailResponse = response.body()
            if (pokemonDetailResponse == null) {
                emit(value = PokemonDetailResult.Error)
                return@flow
            }

            val pokemonDetail = mapper.toDomain(response = pokemonDetailResponse)

            emit(PokemonDetailResult.Success(pokemonDetail = pokemonDetail))

        } catch (_: Exception) {
            emit(value = PokemonDetailResult.Error)
        }
    }
}
