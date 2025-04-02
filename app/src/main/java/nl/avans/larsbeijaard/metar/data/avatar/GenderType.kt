package nl.avans.larsbeijaard.metar.data.avatar

import android.content.Context
import nl.avans.larsbeijaard.metar.R

enum class GenderType { FEMALE, MALE }

fun String.asGenderType(): GenderType {
    // Tricky to use the string resource here. Skipped for brevity.
    return when (this) {
        "Vrouwelijk" -> GenderType.FEMALE
        "Mannelijk" -> GenderType.MALE
        "girl" -> GenderType.FEMALE
        "boy" -> GenderType.MALE
        else -> GenderType.FEMALE
    }
}

fun getAllGenderTypes(): List<GenderType> {
    return listOf(GenderType.FEMALE, GenderType.MALE)
}

fun GenderType.toLocalizedGenderString(context: Context): String {
    return when (this) {
        GenderType.FEMALE -> context.getString(R.string.female)
        GenderType.MALE -> context.getString(R.string.male)
    }
}

fun GenderType.toApiString(): String {
    return when (this) {
        GenderType.FEMALE -> "girl"
        GenderType.MALE -> "boy"
    }
}