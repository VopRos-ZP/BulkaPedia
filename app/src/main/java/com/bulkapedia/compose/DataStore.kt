package com.bulkapedia.compose

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStore (private val context: Context) {

    companion object {
        val Context.dataStore by preferencesDataStore("Settings")

        val EMAIL_KEY = stringPreferencesKey("email")
        val NICKNAME_KEY = stringPreferencesKey("nickname")
        val SIGN_KEY = booleanPreferencesKey("sign")
    }

    val getEmail: Flow<String> = context.dataStore.data.map { it[EMAIL_KEY] ?: "" }
    val getNickname: Flow<String> = context.dataStore.data.map { it[NICKNAME_KEY] ?: "" }
    val getSign: Flow<Boolean> = context.dataStore.data.map { it[SIGN_KEY] ?: false }

    suspend fun saveEmail(email: String) {
        context.dataStore.edit { it[EMAIL_KEY] = email }
    }

    suspend fun saveNickname(nickname: String) {
        context.dataStore.edit { it[NICKNAME_KEY] = nickname }
    }

    suspend fun saveSign(sign: Boolean) {
        context.dataStore.edit { it[SIGN_KEY] = sign }
    }

}

