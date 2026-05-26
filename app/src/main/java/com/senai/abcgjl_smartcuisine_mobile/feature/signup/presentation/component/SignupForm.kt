package com.senai.abcgjl_smartcuisine_mobile.feature.signup.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.component.AppPrimaryButton
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.component.AppTextField
import com.senai.abcgjl_smartcuisine_mobile.core.model.UserRole
import com.senai.abcgjl_smartcuisine_mobile.feature.signup.presentation.state.SignupUiState

@Composable
fun SignupForm(
    uiState: SignupUiState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRoleSelected: (UserRole) -> Unit,
    onCourseSelected: (String) -> Unit,
    onSubmit: () -> Unit,
    onBackToLogin: () -> Unit,
    photoSection: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (uiState.successMessage != null) {
            Text(
                text = uiState.successMessage,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            uiState.generatedEnrollment?.let {
                Text(
                    text = "Matrícula gerada pela API: $it",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = "Tente fazer login depois da aprovação do cadastro na aplicação web.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            OutlinedButton(onClick = onBackToLogin) {
                Text("Voltar para login")
            }
            return@Column
        }

        AppTextField(
            value = uiState.nome,
            label = "Nome completo",
            onValueChange = onNameChange,
            isError = uiState.nomeError != null,
            supportingText = uiState.nomeError
        )

        AppTextField(
            value = uiState.email,
            label = "E-mail",
            onValueChange = onEmailChange,
            isError = uiState.emailError != null,
            supportingText = uiState.emailError
        )

        AppTextField(
            value = uiState.senha,
            label = "Senha",
            onValueChange = onPasswordChange,
            isPassword = true,
            isError = uiState.senhaError != null,
            supportingText = uiState.senhaError
        )

        UserTypeSelector(
            selectedRole = uiState.selectedRole,
            onRoleSelected = onRoleSelected,
            errorMessage = uiState.roleError
        )

        uiState.errorMessage?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error
            )
        }

        AppPrimaryButton(
            text = if (uiState.isSubmitting) "Enviando cadastro..." else "Enviar cadastro",
            onClick = onSubmit,
            enabled = !uiState.isSubmitting
        )
    }
}