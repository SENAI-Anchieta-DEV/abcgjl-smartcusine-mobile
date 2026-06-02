package com.senai.abcgjl_smartcuisine_mobile.feature.signup.data.remote.response

import com.google.gson.annotations.SerializedName

data class SignupResponseDto(
    @SerializedName("enrollment")
    val enrollment: String?,
    @SerializedName("statusCadastro", alternate = ["status"])
    val status: String,
    @SerializedName("mensagem", alternate = ["message"])
    val message: String,
    @SerializedName("token", alternate = ["accessToken"])
    val token: String? = null
)