package com.senai.abcgjl_smartcuisine_mobile.feature.signup.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.senai.abcgjl_smartcuisine_mobile.R
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
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Surface(
                shape = RoundedCornerShape(24.dp),
                color = MaterialTheme.colorScheme.surface, // Dinâmico: Branco no claro, grafite no escuro
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
                        color = MaterialTheme.colorScheme.onSurface // Cor dinâmica para o texto
                    )

                    // --- FORMULÁRIO DIRETO ---

                    // 1. Campo de Seleção do Perfil (Dropdown)
                    var expanded by remember { mutableStateOf(false) }
                    val textoPerfilSelecionado = perfisDisponiveis.find { it.second == uiState.selectedRole }?.first ?: ""

                    Box(modifier = Modifier.fillMaxWidth()) {
                        TextField(
                            value = textoPerfilSelecionado,
                            onValueChange = {},
                            readOnly = true,
                            placeholder = { Text("Selecione o perfil") },
                            shape = RoundedCornerShape(50),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Person,
                                    contentDescription = "Perfil",
                                    tint = Color.Gray
                                )
                            },
                            trailingIcon = {
                                IconButton(onClick = { expanded = true }) {
                                    Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                                }
                            },
                            singleLine = true
                        )

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
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

                    uiState.roleError?.let {
                        Text(text = it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
                    }

                    // 2. Campo Nome Completo
                    TextField(
                        value = uiState.nome,
                        onValueChange = onNameChange,
                        placeholder = { Text("Nome completo") },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = "Nome",
                                tint = Color.Gray
                            )
                        },
                        singleLine = true
                    )
                    uiState.nomeError?.let {
                        Text(text = it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
                    }

                    // 3. Campo E-mail
                    TextField(
                        value = uiState.email,
                        onValueChange = onEmailChange,
                        placeholder = { Text("Email") },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Email,
                                contentDescription = "Email",
                                tint = Color.Gray
                            )
                        },
                        singleLine = true
                    )
                    uiState.emailError?.let {
                        Text(text = it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
                    }

                    // 4. Campo Senha
                    var senhaVisivel by remember { mutableStateOf(false) }
                    TextField(
                        value = uiState.senha,
                        onValueChange = onPasswordChange,
                        placeholder = { Text("Senha") },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        visualTransformation = if (senhaVisivel) VisualTransformation.None else PasswordVisualTransformation(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Lock,
                                contentDescription = "Senha",
                                tint = Color.Gray
                            )
                        },
                        trailingIcon = {
                            val icon = if (senhaVisivel) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                            IconButton(onClick = { senhaVisivel = !senhaVisivel }) {
                                Icon(icon, contentDescription = null)
                            }
                        },
                        singleLine = true
                    )
                    uiState.senhaError?.let {
                        Text(text = it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
                    }

                    uiState.errorMessage?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // 5. Botão de Enviar Cadastro
                    Button(
                        onClick = onSubmit,
                        enabled = !uiState.isSubmitting,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE6863B),
                            disabledContainerColor = Color(0xFFE67E22)
                        )
                    ) {
                        if (uiState.isSubmitting) {
                            CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier.size(24.dp),
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text("Cadastrar", color = Color.White)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

// --- PREVIEWS ---

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

// 🚀 Preview do Tema Escuro Adicionado!
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