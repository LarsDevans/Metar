package nl.avans.larsbeijaard.metar.data.utilities

import nl.avans.larsbeijaard.metar.data.enums.GenderType

fun getAvatarUrl(username: String, gender: GenderType): String {
    val genderString = mapGenderTypeToString(gender)
    return "https://avatar.iran.liara.run/public/$genderString?username=$username"
}

private fun mapGenderTypeToString(gender: GenderType): String {
    return when (gender) {
        GenderType.BOY -> "boy"
        GenderType.GIRL -> "girl"
    }
}
