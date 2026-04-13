package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.model.User
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CadastroViewModel(
    private val repository: UserRepository
) : ViewModel() {
    private val _state = MutableStateFlow<CadastroState>(CadastroState.Idle)
    val state: StateFlow<CadastroState> = _state

    fun cadastrar(user: User) {
        viewModelScope.launch {
            _state.value = CadastroState.Loading

            try {
                val result = repository.cadastrarUsuario(user)

                if (result.isSuccess) {
                    _state.value = CadastroState.Sucesso
                } else {
                    _state.value = CadastroState.Erro(
                        result.exceptionOrNull()?.message ?: "Erro desconhecido"
                    )
                }

            } catch (e: Exception) {
                _state.value = CadastroState.Erro(e.message ?: "Erro desconhecido")
            }
        }
    }
}