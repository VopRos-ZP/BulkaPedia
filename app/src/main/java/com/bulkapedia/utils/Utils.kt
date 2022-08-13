package com.bulkapedia.utils

import android.content.SharedPreferences
import androidx.core.content.edit
import com.bulkapedia.preference.UserPreferences

fun addUserToShared(shared: SharedPreferences, prefs: UserPreferences) {
    shared.edit {
        putBoolean(UserPreferences.SIGNED, prefs.getSigned())
        putString(UserPreferences.LOGIN, prefs.getLogin())
        putString(UserPreferences.PASSWORD, prefs.getLogin())
        putString(UserPreferences.LOGIN, prefs.getLogin())
        putString(UserPreferences.NICKNAME, prefs.getNickname())
        apply()
    }
}
