package com.senai.abcgjl_smartcuisine_mobile.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.senai.abcgjl_smartcuisine_mobile.core.model.ThemeMode
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.themeDataStore by preferencesDataStore(name = "theme_preferences")

@Singleton
class ThemePreferenceStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private companion object {
        val THEME_MODE_KEY = stringPreferencesKey("theme_mode")
    }

    val themeMode: Flow<ThemeMode> = context.themeDataStore.data.map { preferences ->
        preferences[THEME_MODE_KEY]
            ?.let { runCatching { ThemeMode.valueOf(it) }.getOrNull() }
            ?: ThemeMode.SYSTEM
    }

    suspend fun saveThemeMode(themeMode: ThemeMode) {
        context.themeDataStore.edit { preferences: MutablePreferences ->
            preferences[THEME_MODE_KEY] = themeMode.name
        }
    }
}