package nl.avans.larsbeijaard.metar.data

import android.content.Context
import nl.avans.larsbeijaard.metar.data.avatar.DefaultAvatarRepository
import nl.avans.larsbeijaard.metar.data.service.DownloadService

class DefaultAppContainer(
    private val context: Context
) : AppContainer {
    override val avatarRepository: DefaultAvatarRepository by lazy {
        DefaultAvatarRepository(AppDatabase.getDatabase(context).avatarDao())
    }

    override val downloadService: DownloadService by lazy {
        DownloadService(context)
    }
}

interface AppContainer {
    val avatarRepository: DefaultAvatarRepository
    val downloadService: DownloadService
}