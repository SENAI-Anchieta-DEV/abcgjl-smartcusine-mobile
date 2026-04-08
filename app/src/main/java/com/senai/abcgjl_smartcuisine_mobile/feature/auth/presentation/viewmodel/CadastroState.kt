package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel

sealed class CadastroState {
    object Idle : CadastroState()
    object Sucesso : CadastroState()
    data class Erro(val mensagem: String) : CadastroState()
}