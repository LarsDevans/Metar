package nl.avans.larsbeijaard.metar.data.service

fun getAvatarUrl(username: String, gender: String): String {
    return "https://avatar.iran.liara.run/public/${gender}?username=${username}"
}