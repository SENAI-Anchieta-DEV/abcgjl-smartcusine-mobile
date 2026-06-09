package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.abcgjl_smartcuisine_mobile.core.datastore.UserPreferences
import com.senai.abcgjl_smartcuisine_mobile.core.network.api.AuthTokenProvider
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.domain.usecase.LoginUseCase
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.state.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val authTokenProvider: AuthTokenProvider,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _lembrar = MutableStateFlow(false)
    val lembrar: StateFlow<Boolean> = _lembrar.asStateFlow()

    init {
        carregarLoginSalvo()
    }

    fun onLembrarChange(value: Boolean) {
        _lembrar.value = value
    }

    fun onEmailChange(value: String) {
        _uiState.update {
            it.copy(
                email = value,
                emailError = null,
                errorMessage = null
            )
        }
    }

    fun onPasswordChange(value: String) {
        _uiState.update {
            it.copy(
                senha = value,
                senhaError = null,
                errorMessage = null
            )
        }
    }

    fun login() {
        val currentState = _uiState.value

        val emailError =
            if (currentState.email.isBlank()) "Informe o e-mail." else null

        val senhaError =
            if (currentState.senha.isBlank()) "Informe a senha." else null

        if (emailError != null || senhaError != null) {
            _uiState.update {
                it.copy(
                    emailError = emailError,
                    senhaError = senhaError
                )
            }
            return
        }

        viewModelScope.launch {

            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null,
                    isAuthenticated = false
                )
            }

            runCatching {
                loginUseCase(
                    currentState.email.trim(),
                    currentState.senha
                )
            }.onSuccess { tokenResult ->

                authTokenProvider.updateToken(tokenResult)

                if (_lembrar.value) {
                    userPreferences.salvarLogin(
                        email = currentState.email,
                        senha = currentState.senha
                    )
                } else {
                    userPreferences.limparLogin()
                }

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isAuthenticated = true
                    )
                }

            }.onFailure { throwable ->

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = throwable.message
                            ?: "Não foi possível realizar o login."
                    )
                }
            }
        }
    }

    fun consumeAuthentication() {
        _uiState.update {
            it.copy(isAuthenticated = false)
        }
    }

    private fun carregarLoginSalvo() {

        if (userPreferences.isLembrar()) {

            _uiState.update {
                it.copy(
                    email = userPreferences.getEmail() ?: "",
                    senha = userPreferences.getSenha() ?: ""
                )
            }

            _lembrar.value = true
        }
    }
}