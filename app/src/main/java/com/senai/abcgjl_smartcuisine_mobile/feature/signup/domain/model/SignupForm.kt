package com.senai.abcgjl_smartcuisine_mobile.feature.signup.domain.model

import com.senai.abcgjl_smartcuisine_mobile.core.model.UserRole

data class SignupForm(
    val nome: String,
    val email: String,
    val senha: String,
    val role: UserRole
)