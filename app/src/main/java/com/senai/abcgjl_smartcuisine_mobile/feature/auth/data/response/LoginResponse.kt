package com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.response

import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.model.User

data class LoginResponse(
    val token: String,
    val user: User
)