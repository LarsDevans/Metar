package nl.avans.larsbeijaard.metar.data.avatar

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "avatars")
data class Avatar(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val username: String = ""
)