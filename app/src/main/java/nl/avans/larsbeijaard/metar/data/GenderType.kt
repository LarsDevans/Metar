package nl.avans.larsbeijaard.metar.data

import android.content.Context
import nl.avans.larsbeijaard.metar.R

enum class GenderType { MALE, FEMALE }

fun getAllGenderTypes(context: Context): List<String> {
    return listOf(
        context.getString(R.string.female),
        context.getString(R.string.male)
    )
}

fun String.asGenderType(): GenderType {
    // Tricky to use the string resource here. Skipped for brevity.
    return when (this) {
        "Vrouwelijk" -> GenderType.FEMALE
        "Mannelijk" -> GenderType.MALE
        else -> GenderType.FEMALE
    }
}

fun GenderType.toLocalizedGenderString(context: Context): String {
    return when (this) {
        GenderType.MALE -> context.getString(R.string.male)
        GenderType.FEMALE -> context.getString(R.string.female)
    }
}