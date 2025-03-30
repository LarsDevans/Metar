package nl.avans.larsbeijaard.metar.ui.viewmodel.avatar

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import nl.avans.larsbeijaard.metar.data.constant.GenderType

class AvatarViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AvatarUiState())

    fun getAvatarUrl(): String {
        val genderString = _uiState.value.gender.toGenderString()
        // TODO: Delegate this logic to a repository when we have one.
        return "https://avatar.iran.liara.run/public/$genderString?username=${_uiState.value.username}"
    }

    private fun GenderType.toGenderString(): String {
        return when (this) {
            GenderType.MALE -> "boy"
            GenderType.FEMALE -> "girl"
        }
    }
}