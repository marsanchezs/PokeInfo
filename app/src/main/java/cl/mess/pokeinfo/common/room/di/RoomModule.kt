package cl.mess.pokeinfo.common.room.di

import android.content.Context
import androidx.room.Room
import cl.mess.pokeinfo.common.room.data.mapper.FavoritesMapper
import cl.mess.pokeinfo.common.room.data.repository.FavoritesRepositoryImpl
import cl.mess.pokeinfo.common.room.data.source.local.PokeInfoDB
import cl.mess.pokeinfo.common.room.data.source.local.dao.FavoriteDao
import cl.mess.pokeinfo.common.room.domain.repository.FavoritesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val POKE_INFO_DB = "poke_info_db"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, PokeInfoDB::class.java, POKE_INFO_DB).build()

    @Singleton
    @Provides
    fun provideFavoriteDao(db: PokeInfoDB) = db.favoriteDao()

    @Provides
    @Singleton
    fun provideFavoritesMapper(): FavoritesMapper {
        return FavoritesMapper()
    }

    @Provides
    fun provideFavoritesRepository(
        dao: FavoriteDao,
        mapper: FavoritesMapper
    ): FavoritesRepository {
        return FavoritesRepositoryImpl(
            dao = dao,
            mapper = mapper
        )
    }
}
