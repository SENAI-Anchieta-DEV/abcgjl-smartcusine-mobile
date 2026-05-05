package com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.remote.response

data class CadastroRequest(
    val nome: String,
    val email: String,
    val senha: String
)