package nl.avans.larsbeijaard.metar.ui.avatar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import nl.avans.larsbeijaard.metar.data.avatar.Avatar
import nl.avans.larsbeijaard.metar.data.avatar.AvatarRepository
import nl.avans.larsbeijaard.metar.data.avatar.GenderType
import nl.avans.larsbeijaard.metar.data.avatar.asGenderType
import nl.avans.larsbeijaard.metar.data.avatar.toApiString

class AvatarEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val avatarRepository: AvatarRepository
) : ViewModel() {
    var uiState by mutableStateOf(AvatarEditUiState())

    private val avatarId: Int = checkNotNull(savedStateHandle[AvatarEditDestination.AVATAR_ID_ARG])

    init {
        viewModelScope.launch {
            uiState = avatarRepository.getByIdStream(avatarId)
                .filterNotNull()
                .first()
                .toAvatarUiState()
        }
    }

    fun updateUsername(username: String) {
        uiState = uiState.copy(username = username)
    }

    fun updateGender(gender: String) {
        val genderType = gender.asGenderType()
        uiState = uiState.copy(genderType = genderType)
    }

    fun saveAvatarChanges() {
        viewModelScope.launch {
            avatarRepository.updateAvatar(uiState.toAvatar())
        }
    }
}

data class AvatarEditUiState(
    val id: Int = 0,
    val username: String = "",
    val genderType: GenderType = GenderType.FEMALE,
)

fun Avatar.toAvatarUiState(): AvatarEditUiState = AvatarEditUiState(
    id = id,
    username = username,
    genderType = gender.asGenderType()
)

fun AvatarEditUiState.toAvatar(): Avatar = Avatar(
    id = id,
    username = username,
    gender = genderType.toApiString()
)