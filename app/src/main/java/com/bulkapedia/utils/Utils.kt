package com.bulkapedia.utils

import android.content.SharedPreferences
import androidx.core.content.edit
import com.bulkapedia.gears.Effect
import com.bulkapedia.labels.Ranks
import com.bulkapedia.preference.UserPreferences

fun addUserToShared(shared: SharedPreferences, prefs: UserPreferences) {
    shared.edit {
        putBoolean(UserPreferences.SIGNED, prefs.getSigned())
        putString(UserPreferences.EMAIL, prefs.getEmail())
        putString(UserPreferences.PASSWORD, prefs.getPassword())
        putString(UserPreferences.NICKNAME, prefs.getNickname())
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
