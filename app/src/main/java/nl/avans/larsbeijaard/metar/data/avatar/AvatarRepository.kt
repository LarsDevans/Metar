package nl.avans.larsbeijaard.metar.data.avatar

import kotlinx.coroutines.flow.Flow

class DefaultAvatarRepository(
    private val avatarDao: AvatarDao
) : AvatarRepository {
    override fun getAllStream(): Flow<List<Avatar>> = avatarDao.getAll()

    override suspend fun insertAvatar(avatar: Avatar) = avatarDao.insert(avatar)
}

interface AvatarRepository {
    fun getAllStream(): Flow<List<Avatar>>

    suspend fun insertAvatar(avatar: Avatar)
}
