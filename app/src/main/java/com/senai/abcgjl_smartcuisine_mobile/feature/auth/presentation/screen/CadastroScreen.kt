package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.screen

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
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.model.User
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel.CadastroState
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel.CadastroViewModel

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

    // 🔥 NOVO: ViewModel + State
    val viewModel: CadastroViewModel = viewModel()
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    // 🔥 NOVO: Feedback de sucesso/erro
    LaunchedEffect(state) {
        when (state) {
            is CadastroState.Sucesso -> {
                Toast.makeText(context, "Cadastro realizado!", Toast.LENGTH_SHORT).show()
                navController.navigate(Routes.Home.route)
            }

            is CadastroState.Erro -> {
                Toast.makeText(
                    context,
                    (state as CadastroState.Erro).mensagem,
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> {}
        }
    }

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

        // 🔥 ALTERADO: agora chama o ViewModel
        onCadastrarClick = {

            if (senha != confirmarSenha) {
                Toast.makeText(context, "Senhas não coincidem", Toast.LENGTH_SHORT).show()
                return@CadastroContent
            }

            if (nome.isBlank() || email.isBlank()) {
                Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@CadastroContent
            }

            viewModel.cadastrar(
                User(
                    nome = nome,
                    email = email,
                    senha = senha
                )
            )
        },

        modifier = modifier
    )
}