package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.screen

import LoginContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.navigation.Routes
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: LoginViewModel = viewModel()
) {
    var login by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    LoginContent(
        login = login,
        senha = senha,
        onLoginChange = { login = it },
        onSenhaChange = { senha = it },

        onLoginClick = {
            viewModel.login(login, senha) { sucesso ->
                if (sucesso) {
                    navController.navigate(Routes.Home.route)
                } else {
                    println("Erro no login")
                }
            }
        },

        onCadastroClick = {
            navController.navigate(Routes.Cadastro.route)
        },

        modifier = modifier,

        onEsqueciSenhaClick = {
            navController.navigate(Routes.EsqueciSenha.route)
        }

    )
}