package nl.avans.larsbeijaard.metar.ui.home

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

class HomeViewModel(
    private val avatarRepository: AvatarRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                avatarRepository.getAllStream().collect { avatars ->
                    _uiState.update { it.copy(avatarList = avatars) }
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
}

data class HomeUiState(
    val username: String = "Linde",
    val genderType: GenderType = GenderType.FEMALE,
    val avatarList: List<Avatar> = listOf()
)