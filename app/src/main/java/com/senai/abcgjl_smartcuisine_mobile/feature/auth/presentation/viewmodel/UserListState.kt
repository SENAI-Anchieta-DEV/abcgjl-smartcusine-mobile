package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel

import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.model.User

sealed class UserListState {
    object Loading : UserListState()
    data class Sucesso(val lista: List<User>) : UserListState()
    data class Erro(val mensagem: String) : UserListState()
    object Vazio : UserListState()
}