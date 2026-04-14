package com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("nome")
    val nome: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("senha")
    val senha: String,

    @SerializedName("perfil") // Verifique se no seu Backend o nome é exatamente "perfil" ou "role"
    val perfil: String
)