package com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.remote.response

import com.google.gson.annotations.SerializedName

data class MeResponseDto(
    @SerializedName("nome", alternate = ["name"])
    val nome: String? = null,
    val email: String? = null,
    @SerializedName("tipoUsuario", alternate = ["role"])
    val role: String? = null,
    @SerializedName("statusCadastro", alternate = ["status"])
    val status: String? = null,

)