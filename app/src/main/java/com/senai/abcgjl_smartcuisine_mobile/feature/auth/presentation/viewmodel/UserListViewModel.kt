package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.model.User
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserListViewModel(private val repository : UserRepository): ViewModel() {

    private val _state = MutableStateFlow<UserListState>(UserListState.Loading)
    val state: StateFlow<UserListState> = _state

    fun carregarUsuarios() {
        viewModelScope.launch {
            _state.value = UserListState.Loading

            val result = repository.listarUsuarios()

            if (result.isSuccess) {
                val lista = result.getOrNull() ?: emptyList()

                _state.value = if (lista.isEmpty()) {
                    UserListState.Vazio
                } else {
                    UserListState.Sucesso(lista)
                }

            } else {
                _state.value = UserListState.Erro(
                    result.exceptionOrNull()?.message ?: "Erro"
                )
            }
        }
    }
}