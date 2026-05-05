package com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.remote.response

data class UserResponse(
    val id: Long,
    val nome: String,
    val email: String,
    val tipo: String
)