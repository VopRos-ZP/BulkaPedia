package ru.bulkapedia.data.localstore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class LocalStore(private val ctx: Context) {

    companion object {
        private const val NAME = "settings"
        private val Context.dataStore by preferencesDataStore(NAME)
        /** Keys **/
        private val ID_KEY = stringPreferencesKey("id")
        private val SIGN_KEY = booleanPreferencesKey("sign")
    }

    val id = ctx.dataStore.data.map { it[ID_KEY] ?: "" }
    val isSign = ctx.dataStore.data.map { it[SIGN_KEY] ?: false }

    val config = id.combine(isSign) { id, isSign -> Pair(id, isSign) }

    suspend fun saveId(id: String) {
        save(ID_KEY, id)
    }

    suspend fun saveSign(isSign: Boolean) {
        save(SIGN_KEY, isSign)
    }

    private suspend fun <T> save(key: Preferences.Key<T>, value: T) {
        ctx.dataStore.edit { it[key] = value }
    }

}