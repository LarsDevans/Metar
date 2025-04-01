package nl.avans.larsbeijaard.metar.data.avatar

import kotlinx.coroutines.flow.Flow

class DefaultAvatarRepository(
    private val avatarDao: AvatarDao
) : AvatarRepository {
    override fun getAllStream(): Flow<List<Avatar>> = avatarDao.getAll()
    override fun getByIdStream(id: Int): Flow<Avatar?> = avatarDao.getById(id)

    override suspend fun insertAvatar(avatar: Avatar) = avatarDao.insert(avatar)
    override suspend fun deleteAvatar(avatar: Avatar) = avatarDao.delete(avatar)
}

interface AvatarRepository {
    fun getAllStream(): Flow<List<Avatar>>
    fun getByIdStream(id: Int): Flow<Avatar?>

    suspend fun insertAvatar(avatar: Avatar)
    suspend fun deleteAvatar(avatar: Avatar)
}
