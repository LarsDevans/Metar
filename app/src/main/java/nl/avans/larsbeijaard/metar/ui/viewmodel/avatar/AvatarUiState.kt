package nl.avans.larsbeijaard.metar.ui.viewmodel.avatar

import nl.avans.larsbeijaard.metar.data.constant.GenderType

data class AvatarUiState(
    val username: String = "Linde",
    val gender: GenderType = GenderType.FEMALE
)