package cl.mess.pokeinfo.common.room.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.mess.pokeinfo.common.room.data.source.local.entities.Favorite

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(pokemon: Favorite)

    @Delete
    suspend fun delete(pokemon: Favorite)

    @Query("SELECT * FROM favorites")
    suspend fun getAll(): List<Favorite>

    @Query("SELECT * FROM favorites WHERE name = :pokemonName LIMIT 1")
    suspend fun isFavorite(pokemonName: String): Favorite?
}
