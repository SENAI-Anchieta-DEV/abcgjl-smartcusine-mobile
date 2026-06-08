package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.component.LoginContent
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    innerPadding: PaddingValues,
    onNavigateToSignup: () -> Unit,
    onNavigateToHome: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var lembrar by remember { mutableStateOf(false) }

    LoginContent(
        uiState = uiState,
        onEmailChange = { email -> viewModel.onEmailChange(email) },
        onPasswordChange = { senha -> viewModel.onPasswordChange(senha) },
        onLoginClick = { viewModel.login() },
        onSignupClick = onNavigateToSignup,
        onEsqueciSenhaClick = {
            // Espaço reservado para quando você criar a rota do Esqueci Senha
        },
        onEntrarSemCadastroClick = onNavigateToHome,
        lembrar = lembrar,
        onLembrarChange = { lembrar = it },
        modifier = Modifier.padding(innerPadding)
    )
}