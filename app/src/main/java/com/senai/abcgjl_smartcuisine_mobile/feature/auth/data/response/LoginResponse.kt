package com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.response

data class LoginResponse(
    val token: String,
    val success: Boolean,
    val message: String? = null
)