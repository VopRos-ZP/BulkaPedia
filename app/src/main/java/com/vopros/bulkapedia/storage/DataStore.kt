package com.vopros.bulkapedia.storage

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {

        private val Context.dataStore by preferencesDataStore("config")

        private val USER_ID_KEY = stringPreferencesKey("user_id_key")
        private val SIGN_KEY = booleanPreferencesKey("sign_key")
    }

    val userId = context.dataStore.data.map { it[USER_ID_KEY] ?: "" }
    val isSign = context.dataStore.data.map { it[SIGN_KEY] ?: false }

    val config = userId.combine(isSign) { id, isSign -> Pair(id, isSign) }

    suspend fun saveData(userId: String, isSign: Boolean) {
        saveUserId(userId)
        saveIsSign(isSign)
    }

    suspend fun saveUserId(userId: String) {
        context.dataStore.edit { it[USER_ID_KEY] = userId }
    }

    suspend fun saveIsSign(isSign: Boolean) {
        context.dataStore.edit { it[SIGN_KEY] = isSign }
    }

}