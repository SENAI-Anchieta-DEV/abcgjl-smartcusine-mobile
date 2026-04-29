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
    data class Sucesso(val tipoUsuario: String) : LoginState()

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

            // O repositório agora retorna Result<User>
            val result = repository.fazerLogin(email, senha)

            result.onSuccess { user ->
                // O estado agora recebe o tipo vindo do objeto user
                _state.value = LoginState.Sucesso(user.tipoUsuario)
            }.onFailure { e ->
                _state.value = LoginState.Erro(e.message ?: "Erro no login")
            }
        }
    }
}