package com.senai.abcgjl_smartcuisine_mobile.feature.signup.presentation.state

import com.senai.abcgjl_smartcuisine_mobile.core.model.UserRole

data class SignupUiState(
    val nome: String = "",
    val email: String = "",
    val senha: String = "",
    val selectedRole: UserRole? = null,
    val isLoadingCourses: Boolean = false,
    val isSubmitting: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null,
    val generatedEnrollment: String? = null,
    val nomeError: String? = null,
    val emailError: String? = null,
    val senhaError: String? = null,
    val roleError: String? = null,
)