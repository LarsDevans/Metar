package nl.avans.larsbeijaard.metar.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import nl.avans.larsbeijaard.metar.data.avatar.Avatar
import nl.avans.larsbeijaard.metar.data.avatar.AvatarDao

@Database(entities = [Avatar::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun avatarDao(): AvatarDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                val tempInstance = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = AppDatabase::class.java,
                    name = "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                instance = tempInstance
                tempInstance
            }
        }
    }
}