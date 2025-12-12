package cl.mess.pokeinfo.common.room.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey val id: Int,
    val number: Int,
    val name: String,
    val imageUrl: String
)
