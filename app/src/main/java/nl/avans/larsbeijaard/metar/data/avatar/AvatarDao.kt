package nl.avans.larsbeijaard.metar.data.avatar

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AvatarDao {
    @Query("SELECT * FROM avatars")
    fun getAll(): Flow<List<Avatar>>

    @Query("SELECT * FROM avatars WHERE id = :id")
    fun getById(id: Int): Flow<Avatar>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(avatar: Avatar)
}