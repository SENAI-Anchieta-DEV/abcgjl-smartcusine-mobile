package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.screen

import LoginContent
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.navigation.Routes
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel.CadastroViewModelFactory
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel.LoginState
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var login by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    val context = LocalContext.current

    val viewModel: LoginViewModel = viewModel(
        factory = CadastroViewModelFactory(context)
    )

    val state by viewModel.state.collectAsState()

    LaunchedEffect(state) {
        when (state) {
            is LoginState.Sucesso -> {
                Toast.makeText(context, "Login realizado!", Toast.LENGTH_SHORT).show()

                navController.navigate(Routes.Home.route) {
                    popUpTo(Routes.Login.route) { inclusive = true }
                }
            }
            is LoginState.Erro -> {
                val erroMsg = (state as LoginState.Erro).mensagem
                Toast.makeText(context, erroMsg, Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    LoginContent(
        login = login,
        senha = senha,
        onLoginChange = { login = it },
        onSenhaChange = { senha = it },
        isLoading = state is LoginState.Loading,

        onLoginClick = {
            if (login.isBlank() || senha.isBlank()) {
                Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@LoginContent
            }


            viewModel.login(login, senha)
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