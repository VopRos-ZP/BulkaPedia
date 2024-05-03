package ru.bulkapedia.domain.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.CardTravel
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {

    lateinit var resourceManager: ResourceManager

    fun iconNameToVector(name: String): ImageVector {
        return when (name) {
            "hero_info" -> Icons.Default.Person
            "user_sets" -> Icons.Default.CardTravel
            "maps" -> Icons.Default.Map
            "mechanics" -> Icons.Default.Construction
            else -> Icons.Default.Android
        }
    }

    fun secondsToTime(sec: Long): String = SimpleDateFormat("dd:MM HH:mm", Locale.getDefault()).format(
        Date(sec)
    )

//    fun List<Effect>.toRankEffects(): Map<Rank, List<Effect>> = Rank.entries.mapIndexed { i, r ->
//        r to map { e -> e.copy(number = e.ranks[i]) }
//    }.toMap()
//
//    fun HeroStats.toMap(): Map<String, Double> {
//        return mapOf(
//            "maxHealth" to maxHealth.toDouble(),
//            "maxArmor" to maxArmor.toDouble(),
//            "damage" to damage.toDouble(),
//            "vision" to vision.toDouble(),
//            "maxSilence" to maxSilence.toDouble(),
//            "maxSpeed" to maxSpeed.toDouble(),
//            "maxSpeedInFocus" to maxSpeedInFocus.toDouble(),
//            "heroDensity" to heroDensity.toDouble(),
//            "reloadTime" to reloadTime,
//            "fireRate" to fireRate,
//            "fireRange" to fireRange.toDouble(),
//            "fireRangeInFocus" to fireRangeInFocus.toDouble(),
//            "spreadOnStay" to spreadOnStay.toDouble(),
//            "spreadInMove" to spreadInMove.toDouble(),
//            "spreadInFocus" to spreadInFocus.toDouble(),
//            "patrons" to patrons.toDouble(),
//            "powerPenetration" to powerPenetration.toDouble(),
//            "damageOnHealth" to damageOnHealth,
//            "damageOnArmor" to damageOnArmor,
//            "armorPenetration" to armorPenetration.toDouble(),
//            "focusTime" to focusTime
//        )
//    }
//
//    fun Map<String, Double>.toHeroStats(): HeroStats {
//        return HeroStats(
//            this["maxHealth"]!!.toInt(),
//            this["maxArmor"]!!.toInt(),
//            this["damage"]!!.toInt(),
//            this["vision"]!!.toInt(),
//            this["maxSilence"]!!.toInt(),
//            this["maxSpeed"]!!.toInt(),
//            this["maxSpeedInFocus"]!!.toInt(),
//            this["heroDensity"]!!.toInt(),
//            this["reloadTime"]!!.toDouble(),
//            this["fireRate"]!!.toDouble(),
//            this["fireRange"]!!.toInt(),
//            this["fireRangeInFocus"]!!.toInt(),
//            this["spreadOnStay"]!!.toInt(),
//            this["spreadInMove"]!!.toInt(),
//            this["spreadInFocus"]!!.toInt(),
//            this["patrons"]!!.toInt(),
//            this["powerPenetration"]!!.toInt(),
//            this["damageOnHealth"]!!.toDouble(),
//            this["damageOnArmor"]!!.toDouble(),
//            this["armorPenetration"]!!.toInt(),
//            this["focusTime"]!!.toDouble(),
//        )
//    }

}