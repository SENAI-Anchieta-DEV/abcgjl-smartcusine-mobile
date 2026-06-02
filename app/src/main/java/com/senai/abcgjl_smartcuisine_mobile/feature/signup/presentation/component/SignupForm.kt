package com.senai.abcgjl_smartcuisine_mobile.feature.signup.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senai.abcgjl_smartcuisine_mobile.R
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.component.AppPrimaryButton
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.component.AppTextField
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.SmartCuisineTheme
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.ui.components.background.AuthGradientBackground
import com.senai.abcgjl_smartcuisine_mobile.core.model.UserRole
import com.senai.abcgjl_smartcuisine_mobile.feature.signup.presentation.state.SignupUiState

@Composable
fun SignupForm(
    uiState: SignupUiState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRoleSelected: (UserRole) -> Unit,
    onSubmit: () -> Unit,
    onBackToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    val perfisDisponiveis = listOf(
        "Administrador" to UserRole.ADMINISTRADOR,
        "Gerente" to UserRole.GERENTE,
        "Cozinheiro" to UserRole.COZINHEIRO
    )

    Box(modifier = modifier.fillMaxSize()) {
        AuthGradientBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Image(
                painter = painterResource(id = R.drawable.logosmartcuisine),
                contentDescription = "logo smartcuisine",
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Surface(
                shape = RoundedCornerShape(24.dp),
                color = Color.White,
                tonalElevation = 8.dp,
                shadowElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Cadastro",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    var expanded by remember { mutableStateOf(false) }
                    val textoPerfilSelecionado = perfisDisponiveis.find { it.second == uiState.selectedRole }?.first ?: ""

                    Box(modifier = Modifier.fillMaxWidth()) {
                        AppTextField(
                            value = textoPerfilSelecionado,
                            label = "Selecione o perfil",
                            onValueChange = {},
                            isError = uiState.roleError != null,
                            supportingText = uiState.roleError,
                        )

                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .clickable { expanded = true }
                        )

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.fillMaxWidth(0.7f)
                        ) {
                            perfisDisponiveis.forEach { (label, role) ->
                                DropdownMenuItem(
                                    text = { Text(label) },
                                    onClick = {
                                        onRoleSelected(role)
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                    AppTextField(
                        value = uiState.nome,
                        label = "Nome completo",
                        onValueChange = onNameChange,
                        isError = uiState.nomeError != null,
                        supportingText = uiState.nomeError,
                    )

                    AppTextField(
                        value = uiState.email,
                        label = "E-mail",
                        onValueChange = onEmailChange,
                        isError = uiState.emailError != null,
                        supportingText = uiState.emailError,
                    )

                    AppTextField(
                        value = uiState.senha,
                        label = "Senha",
                        onValueChange = onPasswordChange,
                        isPassword = true,
                        isError = uiState.senhaError != null,
                        supportingText = uiState.senhaError,
                    )

                    uiState.errorMessage?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.align(Alignment.Start)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    
                    AppPrimaryButton(
                        text = if (uiState.isSubmitting) "Cadastrando..." else "Cadastrar",
                        onClick = onSubmit,
                        enabled = !uiState.isSubmitting,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    )

                    Button(
                        onClick = onBackToLogin,
                        enabled = !uiState.isSubmitting,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE0E0E0),
                            contentColor = Color.Black
                        )
                    ) {
                        Text("Voltar para o Login", fontSize = 14.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSignupClaro() {
    SmartCuisineTheme(darkTheme = false) {
        SignupForm(
            uiState = SignupUiState(),
            onNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onRoleSelected = {},
            onSubmit = {},
            onBackToLogin = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSignupEscuro() {
    SmartCuisineTheme(darkTheme = true) {
        SignupForm(
            uiState = SignupUiState(),
            onNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onRoleSelected = {},
            onSubmit = {},
            onBackToLogin = {}
        )
    }
}