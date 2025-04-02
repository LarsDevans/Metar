package nl.avans.larsbeijaard.metar.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.avans.larsbeijaard.metar.data.avatar.GenderType
import nl.avans.larsbeijaard.metar.data.avatar.asGenderType
import nl.avans.larsbeijaard.metar.data.avatar.Avatar
import nl.avans.larsbeijaard.metar.data.avatar.AvatarRepository
import nl.avans.larsbeijaard.metar.data.avatar.toApiString
import nl.avans.larsbeijaard.metar.data.service.DownloadService
import nl.avans.larsbeijaard.metar.data.service.getAvatarUrl

class HomeViewModel(
    private val avatarRepository: AvatarRepository,
    private val downloadService: DownloadService
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                avatarRepository.getAllStream().collect { avatars ->
                    _uiState.update { avatar -> avatar.copy(avatarList = avatars.sortedByDescending { it.id }) }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(avatarList = emptyList()) }
            }
        }
    }

    fun updateUsername(username: String) {
        _uiState.update { it.copy(username = username) }
    }

    fun updateGender(gender: String) {
        val genderType = gender.asGenderType()
        _uiState.update { it.copy(genderType = genderType) }
    }

    fun saveAvatar() {
        viewModelScope.launch {
            val avatar = Avatar(
                username = _uiState.value.username,
                gender = _uiState.value.genderType.toApiString()
            )

            val existingAvatar = _uiState.value.avatarList.find {
                it.username == avatar.username && it.gender == avatar.gender
            }
            existingAvatar?.let { avatarRepository.deleteAvatar(it) }

            avatarRepository.insertAvatar(avatar = avatar)
        }
    }

    fun deleteAvatar(avatar: Avatar) {
        viewModelScope.launch {
            avatarRepository.deleteAvatar(avatar = avatar)
        }
    }

    fun downloadAvatar(avatar: Avatar = Avatar(
        username = _uiState.value.username,
        gender = _uiState.value.genderType.toApiString()
    )) {
        viewModelScope.launch {
            val url = getAvatarUrl(
                username = avatar.username,
                gender = avatar.gender
            )

            downloadService.downloadFile(url, "${avatar.username}_${avatar.gender}")
        }
    }
}

data class HomeUiState(
    val username: String = "Linde",
    val genderType: GenderType = GenderType.FEMALE,
    val avatarList: List<Avatar> = listOf()
)