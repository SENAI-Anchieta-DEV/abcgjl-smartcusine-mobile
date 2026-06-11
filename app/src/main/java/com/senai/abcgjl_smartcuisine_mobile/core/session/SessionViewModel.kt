package com.senai.abcgjl_smartcuisine_mobile.core.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.abcgjl_smartcuisine_mobile.core.datastore.ThemePreferenceStore
import com.senai.abcgjl_smartcuisine_mobile.core.model.SessionUser
import com.senai.abcgjl_smartcuisine_mobile.core.model.ThemeMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val themePreferenceStore: ThemePreferenceStore
) : ViewModel() {

    val sessionState: StateFlow<SessionState> = sessionManager.sessionState
    val sessionUser: StateFlow<SessionUser> = sessionManager.sessionUser

    private val _navigateToLogin = MutableStateFlow(false)
    val navigateToLogin: StateFlow<Boolean> = _navigateToLogin.asStateFlow()

    val themeMode: StateFlow<ThemeMode> = themePreferenceStore.themeMode
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.WhileSubscribed(5_000),
            initialValue = ThemeMode.SYSTEM
        )

    fun logout() {
        sessionManager.logout()
        _navigateToLogin.value = true
    }

    fun consumeNavigateToLogin() {
        _navigateToLogin.value = false
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