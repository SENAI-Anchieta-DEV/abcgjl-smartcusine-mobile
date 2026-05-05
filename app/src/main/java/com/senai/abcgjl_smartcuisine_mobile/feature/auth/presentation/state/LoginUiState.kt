package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.state

data class LoginUiState(
    val email: String = "",
    val senha: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isAuthenticated: Boolean = false,
    val emailError: String? = null,
    val senhaError: String? = null
)