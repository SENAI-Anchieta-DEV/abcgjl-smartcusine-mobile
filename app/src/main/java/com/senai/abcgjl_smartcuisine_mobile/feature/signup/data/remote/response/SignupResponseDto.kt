package com.senai.abcgjl_smartcuisine_mobile.feature.signup.data.remote.response

import com.google.gson.annotations.SerializedName

data class SignupResponseDto(
    val id: Long,
    val nome: String,
    val email: String,
    val tipo: String
)