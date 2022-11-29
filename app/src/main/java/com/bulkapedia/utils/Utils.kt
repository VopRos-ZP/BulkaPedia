package com.bulkapedia.utils

import android.content.SharedPreferences
import androidx.core.content.edit
import com.bulkapedia.ICON_LIST
import com.bulkapedia.MAIN
import com.bulkapedia.R
import com.bulkapedia.data.gears.Effect
import com.bulkapedia.data.labels.Ranks
import com.bulkapedia.preference.UserPreferences

val HEROES_RES = mapOf(
    "arnie" to R.drawable.arnie_icon,
    "sparkle" to R.drawable.sparkle_icon,
    "cyclops" to R.drawable.cyclops_icon,
    "hurricane" to R.drawable.hurricane_icon,

    "ghost" to R.drawable.ghost_icon,
    "freddie" to R.drawable.freddie_icon,
    "angel" to R.drawable.angel_icon,
    "raven" to R.drawable.raven_icon,

    "blot" to R.drawable.blot_icon,
    "firefly" to R.drawable.firefly_icon,
    "slayer" to R.drawable.slayer_icon,
    "lynx" to R.drawable.lynx_icon,
    "mirage" to R.drawable.mirage_icon,

    "smog" to R.drawable.smog_icon,
    "dragoon" to R.drawable.dragoon_icon,
    "bastion" to R.drawable.bastion_icon,
    "bertha" to R.drawable.bertha_icon,
    "leviathan" to R.drawable.leviathan_icon,

    "stalker" to R.drawable.stalker_icon,
    "doc" to R.drawable.doc_icon,
    "levi" to R.drawable.levi_icon,
    "satoshi" to R.drawable.satoshi_icon,
    // "tess" to R.drawable.arnie_icon // new hero
)

lateinit var GEARS_RES: Map<String, Int>

fun addUserToShared(shared: SharedPreferences, prefs: UserPreferences) {
    shared.edit {
        putBoolean(UserPreferences.SIGNED, prefs.getSigned())
        putString(UserPreferences.EMAIL, prefs.getEmail())
        putString(UserPreferences.PASSWORD, prefs.getPassword())
        putString(UserPreferences.NICKNAME, prefs.getNickname())
        apply()
    }
}

fun updateBoolShared() {
    MAIN.getPreferences().edit {
        putBoolean(UserPreferences.SIGNED, MAIN.prefs.getSigned())
        putBoolean(UserPreferences.NEWS, MAIN.prefs.getNews())
        apply()
    }
}

fun autoFillGearEffects(effects: List<Effect>, ints: Map<Int, List<Int>>): Map<Ranks, List<Effect>> {
    val m = mutableMapOf<Ranks, List<Effect>>()
    Ranks.values().forEachIndexed { i, r ->
        m += r to effects.mapIndexed { ei, it ->
            Effect(ints[ei]!![i], it.percent, it.description)
        }
    }
    return m
}

fun stringToResource(str: String): Int = ICON_LIST[str]!!

fun resourceToString(res: Int): String {
    return ICON_LIST.map { it.value to it.key }.toMap()[res] ?: ""
}
