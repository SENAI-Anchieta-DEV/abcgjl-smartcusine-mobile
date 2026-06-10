package com.senai.abcgjl_smartcuisine_mobile.feature.signup.data.remote.request

import com.google.gson.annotations.SerializedName

data class SignupRequestDto(
    @SerializedName("nome") val nome: String,
    @SerializedName("email") val email: String,
    @SerializedName("senha") val senha: String,
    @SerializedName("tipoUsuario") val tipoUsuario: String
)