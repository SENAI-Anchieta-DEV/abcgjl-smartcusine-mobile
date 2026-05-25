package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.layout.AppScreen
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.component.LoginForm
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.component.LoginHeader
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToSignup: () -> Unit,
    innerPadding: PaddingValues = PaddingValues(),
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.isAuthenticated) {
        if (uiState.isAuthenticated) {
            onLoginSuccess()
            viewModel.consumeAuthentication()
        }
    }

    AppScreen(innerPadding = innerPadding) {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            LoginHeader()
            LoginForm(
                uiState = uiState,
                onEmailChange = viewModel::onEmailChange,
                onPasswordChange = viewModel::onPasswordChange,
                onLoginClick = viewModel::login,
                onSignupClick = onNavigateToSignup
            )
        }
    }
}