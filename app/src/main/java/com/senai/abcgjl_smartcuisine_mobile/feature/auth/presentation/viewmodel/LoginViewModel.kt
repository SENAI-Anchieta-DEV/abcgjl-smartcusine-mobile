package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.abcgjl_smartcuisine_mobile.core.network.api.RetrofitInstance
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.repository.UserRepository
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.response.LoginRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Sucesso : LoginState()
    data class Erro(val mensagem: String) : LoginState()
}

class LoginViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    val state: StateFlow<LoginState> = _state

    fun login(email: String, senha: String) {
        viewModelScope.launch {
            _state.value = LoginState.Loading
            try {
                val result = repository.login(email, senha)

                if (result.isSuccess) {
                    _state.value = LoginState.Sucesso
                } else {
                    _state.value = LoginState.Erro(
                        result.exceptionOrNull()?.message ?: "Erro ao fazer login"
                    )
                }

            } catch (e: Exception) {
                _state.value = LoginState.Erro(e.message ?: "Erro de conexão")
            }
        }
    }
}