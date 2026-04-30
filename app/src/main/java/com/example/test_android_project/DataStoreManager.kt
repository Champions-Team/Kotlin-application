package com.example.test_android_project

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("auth")

class DataStoreManager(private val context: Context) {

    companion object {
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
        private val TOKEN_TYPE_KEY = stringPreferencesKey("token_type")
        private val USER_EMAIL_KEY = stringPreferencesKey("user_email")
    }

    suspend fun saveTokens(
        accessToken: String,
        refreshToken: String,
        tokenType: String,
        userEmail: String
    ) {
        context.dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN_KEY] = accessToken
            preferences[REFRESH_TOKEN_KEY] = refreshToken
            preferences[TOKEN_TYPE_KEY] = tokenType
            preferences[USER_EMAIL_KEY] = userEmail
        }
    }

    suspend fun getAccessToken(): String? {
        return context.dataStore.data.map { preferences ->
            preferences[ACCESS_TOKEN_KEY]
        }.firstOrNull()
    }

    suspend fun clearTokens() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}