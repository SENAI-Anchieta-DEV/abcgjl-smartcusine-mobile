package com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.remote.request

import com.google.gson.annotations.SerializedName

data class LoginRequestDto(
    val email: String,
    @SerializedName("senha")
    val senha: String,
    val tipoUsuario: String = "GERENTE"
)