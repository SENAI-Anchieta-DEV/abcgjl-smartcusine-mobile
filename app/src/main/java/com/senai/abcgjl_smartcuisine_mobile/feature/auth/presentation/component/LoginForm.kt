package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginForm(
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onSignupClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp)
    ) {
        AppTextField(
            value = uiState.email,
            label = "E-mail",
            onValueChange = onEmailChange,
            isError = uiState.emailError != null,
            supportingText = uiState.emailError
        )

        AppTextField(
            value = uiState.password,
            label = "Senha",
            onValueChange = onPasswordChange,
            isPassword = true,
            isError = uiState.passwordError != null,
            supportingText = uiState.passwordError
        )

        uiState.errorMessage?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error
            )
        }

        Text(
            text = "Use as credenciais cadastradas na sua API para entrar no aplicativo.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        AppPrimaryButton(
            text = if (uiState.isLoading) "Entrando..." else "Entrar",
            onClick = onLoginClick,
            enabled = !uiState.isLoading
        )

        TextButton(
            onClick = onSignupClick,
            enabled = !uiState.isLoading
        ) {
            Text("Ainda não tem cadastro? Criar agora")
        }
    }
}