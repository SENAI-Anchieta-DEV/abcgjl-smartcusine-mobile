package com.senai.abcgjl_smartcuisine_mobile.core.session

import com.senai.abcgjl_smartcuisine_mobile.core.datastore.SessionPreferenceStore
import com.senai.abcgjl_smartcuisine_mobile.core.model.SessionUser
import com.senai.abcgjl_smartcuisine_mobile.core.network.api.AuthTokenProvider
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Singleton
class SessionManager @Inject constructor(
    private val sessionPreferenceStore: SessionPreferenceStore,
    private val authTokenProvider: AuthTokenProvider
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _sessionState = MutableStateFlow<SessionState>(SessionState.Loading)
    val sessionState: StateFlow<SessionState> = _sessionState.asStateFlow()

    private val _sessionUser = MutableStateFlow(SessionUser())
    val sessionUser: StateFlow<SessionUser> = _sessionUser.asStateFlow()

    init {
        scope.launch {
            sessionPreferenceStore.sessionUser.collect { storedUser ->
                if (storedUser == null) {
                    authTokenProvider.updateToken(null)
                    _sessionUser.value = SessionUser()
                    _sessionState.value = SessionState.Unauthenticated
                } else {
                    authTokenProvider.updateToken(storedUser.authToken)
                    _sessionUser.value = storedUser
                    _sessionState.value = SessionState.Authenticated
                }
            }
        }
    }

    fun createSession(user: SessionUser) {
        authTokenProvider.updateToken(user.authToken)
        scope.launch {
            sessionPreferenceStore.saveSessionUser(user)
        }
    }

    fun logout() {
        authTokenProvider.updateToken(null)
        scope.launch {
            sessionPreferenceStore.clear()
        }
    }
}