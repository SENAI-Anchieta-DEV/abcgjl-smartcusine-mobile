package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.state.LoginUiState


@Composable
fun LoginContent(
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onSignupClick: () -> Unit,
    onEsqueciSenhaClick: () -> Unit,
    onEntrarSemCadastroClick: () -> Unit,
    lembrar: Boolean,
    onLembrarChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {

        AuthGradientBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
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
                        text = "Login",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(4.dp))

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

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = lembrar,
                                onCheckedChange = onLembrarChange,
                                colors = CheckboxDefaults.colors(
                                    uncheckedColor = Color.Gray
                                )
                            )
                            Text("Lembrar senha", fontSize = 12.sp, color = Color.DarkGray)
                        }
                        Text(
                            text = "Esqueceu a senha?",
                            fontSize = 12.sp,
                            color = Color(0xFF4A90E2),
                            modifier = Modifier.clickable { onEsqueciSenhaClick() }
                        )
                    }

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
                        text = if (uiState.isLoading) "Entrando..." else "Entrar",
                        onClick = onLoginClick,
                        enabled = !uiState.isLoading,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    )


                    Button(
                        onClick = onSignupClick,
                        enabled = !uiState.isLoading,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE0E0E0),
                            contentColor = Color.Black
                        )
                    ) {
                        Text("Cadastre-se", fontSize = 14.sp)
                    }

                    Text(
                        text = "Entrar sem cadastro",
                        fontSize = 13.sp,
                        color = Color(0xFF4A90E2),
                        modifier = Modifier.clickable {
                            onEntrarSemCadastroClick()
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginContentClaro() {
    SmartCuisineTheme(darkTheme = false) {
        LoginContent(
            uiState = LoginUiState(email = "jamily@adm.com", senha = "123456"),
            onEmailChange = {},
            onPasswordChange = {},
            onLoginClick = {},
            onSignupClick = {},
            onEsqueciSenhaClick = {},
            onEntrarSemCadastroClick = {},
            lembrar = true,
            onLembrarChange = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginContentEscuro() {
    SmartCuisineTheme(darkTheme = true) {
        LoginContent(
            uiState = LoginUiState(),
            onEmailChange = {},
            onPasswordChange = {},
            onLoginClick = {},
            onSignupClick = {},
            onEsqueciSenhaClick = {},
            onEntrarSemCadastroClick = {},
            lembrar = false,
            onLembrarChange = {}
        )
    }
}