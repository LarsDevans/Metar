package nl.avans.larsbeijaard.metar.data

import android.content.Context
import nl.avans.larsbeijaard.metar.data.avatar.DefaultAvatarRepository

class DefaultAppContainer(
    private val context: Context
) : AppContainer {
    override val avatarRepository: DefaultAvatarRepository by lazy {
        DefaultAvatarRepository(AppDatabase.getDatabase(context).avatarDao())
    }
}

interface AppContainer {
    val avatarRepository: DefaultAvatarRepository
}