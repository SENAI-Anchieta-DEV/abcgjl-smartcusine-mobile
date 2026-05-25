package com.senai.abcgjl_smartcuisine_mobile.core.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.abcgjl_smartcuisine_mobile.core.datastore.ThemePreferenceStore
import com.senai.abcgjl_smartcuisine_mobile.core.session.SessionManager
import com.senai.abcgjl_smartcuisine_mobile.core.session.SessionState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val themePreferenceStore: ThemePreferenceStore
) : ViewModel() {

    val sessionState: StateFlow<SessionState> = sessionManager.sessionState
    val sessionUser: StateFlow<SessionUser> = sessionManager.sessionUser

    val themeMode: StateFlow<ThemeMode> = themePreferenceStore.themeMode
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ThemeMode.SYSTEM
        )

    fun logout() {
        sessionManager.logout()
    }

    fun toggleTheme() {
        viewModelScope.launch {
            val newMode = when (themeMode.value) {
                ThemeMode.DARK -> ThemeMode.LIGHT
                ThemeMode.LIGHT -> ThemeMode.DARK
                ThemeMode.SYSTEM -> ThemeMode.DARK
            }
            themePreferenceStore.saveThemeMode(newMode)
        }
    }
}