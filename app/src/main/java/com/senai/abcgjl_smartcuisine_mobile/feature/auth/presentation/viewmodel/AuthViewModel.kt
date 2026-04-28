package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.repository.AuthRepository
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.response.CadastroRequest
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    // State para avisar a tela sobre o status do cadastro
    var cadastroStatus by mutableStateOf<String?>(null)
        private set

    fun realizarCadastro(request: CadastroRequest) {
        viewModelScope.launch {
            try {
                val response = repository.cadastrar(request)
                if (response.isSuccessful) {
                    cadastroStatus = "Sucesso"
                } else {
                    // Aqui pegamos o erro do servidor (o erro 500 que você via)
                    val errorMsg = response.errorBody()?.string() ?: "Erro desconhecido"
                    cadastroStatus = "Erro: $errorMsg"
                }
            } catch (e: Exception) {
                cadastroStatus = "Falha na conexão: ${e.message}"
            }
        }
    }
}