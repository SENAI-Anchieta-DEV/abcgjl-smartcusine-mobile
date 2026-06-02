package com.senai.abcgjl_smartcuisine_mobile.feature.signup.data.remote.request

data class SignupRequestDto(
    val name: String,
    val email: String,
    val password: String,
    val role: String
)