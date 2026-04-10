package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel.UserListState
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel.UserListViewModel

@Composable
fun UserListScreen(
    perfil: String, // vem da tela anterior ou usuário logado
    viewModel: UserListViewModel = viewModel()
) {

    // VERIFICA PERMISSÃO AQUI
    if (perfil != "Administrador") {
        Text("Sem permissão")
        return
    }

    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.carregarUsuarios()
    }

    when (state) {
        is UserListState.Loading -> Text("Carregando...")
        is UserListState.Erro -> Text("Erro")
        is UserListState.Vazio -> Text("Nenhum usuário")
        is UserListState.Sucesso -> {
            val lista = (state as UserListState.Sucesso).lista

            LazyColumn {
                items(lista) { user ->
                    Column {
                        Text(user.nome)
                        Text(user.email)
                    }
                }
            }
        }
    }
}