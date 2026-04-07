package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.navigation.Routes
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.Content.CadastroContent

@Composable
fun CadastroScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }
    var perfil by remember { mutableStateOf("Administrador") }

    CadastroContent(
        nome = nome,
        email = email,
        senha = senha,
        confirmarSenha = confirmarSenha,
        perfil = perfil,
        onNomeChange = { nome = it },
        onEmailChange = { email = it },
        onSenhaChange = { senha = it },
        onConfirmarSenhaChange = { confirmarSenha = it },
        onPerfilChange = { perfil = it },
        onCadastrarClick = {
            if (senha == confirmarSenha && nome.isNotBlank() && email.isNotBlank()) {
                navController.navigate(Routes.Home.route)
            }
        },
        modifier = modifier
    )
}