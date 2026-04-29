package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.screen

import LoginContent
import android.content.Context
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
import com.senai.abcgjl_smartcuisine_mobile.core.network.datastore.UserPreferences
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
    var lembrar by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val userPrefs = UserPreferences(context)

    val viewModel: LoginViewModel = viewModel(
        factory = CadastroViewModelFactory(context)
    )

    val state by viewModel.state.collectAsState()

    LaunchedEffect(state) {
        when (val currentState = state) {
            is LoginState.Sucesso -> {
                Toast.makeText(context, "Bem-vindo!", Toast.LENGTH_SHORT).show()

                // Aqui usamos o tipo que veio do estado de sucesso
                val rota = when (currentState.tipoUsuario) {
                    "ADMIN" -> Routes.HomeAdmin.route
                    "GERENTE" -> Routes.HomeGerente.route
                    "COZINHEIRO" -> Routes.HomeCozinheiro.route
                    else -> Routes.HomeAdmin.route // Rota padrão home
                }

                navController.navigate(rota) {
                    popUpTo(Routes.Login.route) { inclusive = true }
                }
            }
            is LoginState.Erro -> {
                Toast.makeText(context, currentState.mensagem, Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    LaunchedEffect(Unit) {
        if (userPrefs.isLembrar()) {
            login = userPrefs.getEmail() ?: ""
            senha = userPrefs.getSenha() ?: ""
            lembrar = true

            viewModel.login(login, senha)
        }
    }

    LoginContent(
        login = login,
        senha = senha,
        onLoginChange = { login = it },
        onSenhaChange = { senha = it },
        lembrar = lembrar,
        onLembrarChange = { lembrar = it },
        isLoading = state is LoginState.Loading,

        onLoginClick = {
            if (login.isBlank() || senha.isBlank()) {
                Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@LoginContent
            }

            if (lembrar) {
                userPrefs.salvarLogin(login, senha)
            } else {
                userPrefs.limparLogin()
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