package nl.avans.larsbeijaard.metar.data.utilities

import nl.avans.larsbeijaard.metar.data.enums.GenderType

fun getAvatarUrl(username: String, gender: GenderType): String {
    return "https://avatar.iran.liara.run/public/$gender?username=$username"
}
