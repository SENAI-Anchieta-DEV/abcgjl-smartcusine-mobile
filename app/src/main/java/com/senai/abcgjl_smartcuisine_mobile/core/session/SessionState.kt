package com.senai.abcgjl_smartcuisine_mobile.core.session

sealed interface SessionState {
    data object Loading : SessionState
    data object Authenticated : SessionState
    data object Unauthenticated : SessionState
}