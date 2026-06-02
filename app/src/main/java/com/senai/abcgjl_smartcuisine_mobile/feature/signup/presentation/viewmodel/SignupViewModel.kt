package com.senai.abcgjl_smartcuisine_mobile.feature.signup.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.abcgjl_smartcuisine_mobile.core.model.UserRole
import com.senai.abcgjl_smartcuisine_mobile.core.network.api.AuthTokenProvider
import com.senai.abcgjl_smartcuisine_mobile.feature.signup.domain.model.SignupForm
import com.senai.abcgjl_smartcuisine_mobile.feature.signup.domain.usecase.RegisterUserUseCase
import com.senai.abcgjl_smartcuisine_mobile.feature.signup.presentation.state.SignupUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val authTokenProvider: AuthTokenProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignupUiState())
    val uiState: StateFlow<SignupUiState> = _uiState.asStateFlow()

    fun onNameChange(value: String) {
        _uiState.update { it.copy(nome = value, nomeError = null, errorMessage = null) }
    }

    fun onEmailChange(value: String) {
        _uiState.update { it.copy(email = value, emailError = null, errorMessage = null) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(senha = value, senhaError = null, errorMessage = null) }
    }

    fun onRoleSelected(role: UserRole) {
        _uiState.update {
            it.copy(
                selectedRole = role,
                roleError = null,
                errorMessage = null
            )
        }
    }


    fun submit() {
        val current = _uiState.value
        val nomeError = if (current.nome.isBlank()) "Informe o nome." else null
        val emailError = when {
            current.email.isBlank() -> "Informe o e-mail."
            !current.email.contains("@") -> "Informe um e-mail válido."
            else -> null
        }
        val senhaError = if (current.senha.length < 6) "A senha deve ter ao menos 6 caracteres." else null
        val roleError = if (current.selectedRole == null) "Escolha o tipo de usuário." else null

        if (listOf(nomeError, emailError, senhaError, roleError).any { it != null }) {
            _uiState.update {
                it.copy(
                    nomeError = nomeError,
                    emailError = emailError,
                    senhaError = senhaError,
                    roleError = roleError
                )
            }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isSubmitting = true, errorMessage = null, successMessage = null) }

            runCatching {
                registerUserUseCase(
                    SignupForm(
                        nome = current.nome.trim(),
                        email = current.email.trim(),
                        senha = current.senha,
                        role = current.selectedRole!!
                    )
                )
            }.onSuccess { result ->
                result.token?.let { token ->
                    authTokenProvider.updateToken(token)
                }

                _uiState.update {
                    SignupUiState(
                        successMessage = result.message,
                        generatedEnrollment = result.enrollment
                    )
                }
            }.onFailure { throwable ->
                _uiState.update {
                    it.copy(
                        isSubmitting = false,
                        errorMessage = throwable.message ?: "Não foi possível enviar o cadastro."
                    )
                }
            }
        }
    }
}