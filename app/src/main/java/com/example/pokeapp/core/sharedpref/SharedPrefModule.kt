package com.example.pokeapp.core.sharedpref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

@Singleton
class SharedPrefModule @Inject constructor(
    @param:ApplicationContext private val context: Context,
) {
    companion object Companion {
        val IS_LOGGED_IN = booleanPreferencesKey("IS_LOGGED_IN")
        val USERNAME = stringPreferencesKey("USERNAME")
    }

    fun isLoggedIn(): Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_LOGGED_IN] ?: false
        }

    fun username(): Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[USERNAME] ?: ""
        }

    suspend fun saveLoginInfo(isLoggedIn: Boolean, username: String) {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = isLoggedIn
            preferences[USERNAME] = username
        }
    }

    suspend fun clearLoginInfo() {
        context.dataStore.edit { preferences ->
            preferences.remove(IS_LOGGED_IN)
            preferences.remove(USERNAME)
        }
    }
}