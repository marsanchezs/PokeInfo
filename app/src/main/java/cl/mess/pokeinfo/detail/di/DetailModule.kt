package cl.mess.pokeinfo.detail.di

import cl.mess.pokeinfo.detail.data.repository.DetailRepositoryImpl
import cl.mess.pokeinfo.detail.domain.repository.DetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DetailModule {

    @Binds
    @Singleton
    abstract fun bindDetailRepository(
        impl: DetailRepositoryImpl
    ): DetailRepository
}
