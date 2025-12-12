package cl.mess.pokeinfo.common.room.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.mess.pokeinfo.common.room.data.source.local.dao.FavoriteDao
import cl.mess.pokeinfo.common.room.data.source.local.entities.Favorite

@Database(entities = [Favorite::class], version = 1)
abstract class PokeInfoDB : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
