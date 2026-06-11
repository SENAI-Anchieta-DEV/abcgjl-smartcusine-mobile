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
    onNavigateToEsqueciSenha: () -> Unit,
    onNavigateAsGuest: () -> Unit = {},
    viewModel: LoginViewModel = hiltViewModel()

) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lembrar by viewModel.lembrar.collectAsStateWithLifecycle()

    LoginContent(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onLoginClick = viewModel::login,
        onSignupClick = onNavigateToSignup,
        onEsqueciSenhaClick = onNavigateToEsqueciSenha,
        onEntrarSemCadastroClick = onNavigateAsGuest,
        lembrar = lembrar,
        onLembrarChange = viewModel::onLembrarChange,
        modifier = Modifier.padding(innerPadding)
    )
}