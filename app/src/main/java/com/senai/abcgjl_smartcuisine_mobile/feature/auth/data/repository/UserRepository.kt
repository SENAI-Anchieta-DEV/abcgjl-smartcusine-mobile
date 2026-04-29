package com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.repository


import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.model.User
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.response.LoginRequest
import android.util.Log
import com.senai.abcgjl_smartcuisine_mobile.core.network.datastore.UserPreferences

class UserRepository(
    private val api: com.senai.abcgjl_smartcuisine_mobile.core.network.api.ApiService
    ,
    private val userPreferences: UserPreferences
) {

    suspend fun fazerLogin(email: String, senha: String): Result<User> {
        return try {
            val request = LoginRequest(email, senha)
            val response = api.login(request)

            if (response.isSuccessful && response.body() != null) {
                val loginResponse = response.body()!!

                // Salvamos o token
                userPreferences.saveToken(loginResponse.token)

                // Retornamos o objeto User que veio na resposta
                Result.success(loginResponse.user)
            } else {
                Result.failure(Exception("Email ou senha incorretos"))
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Erro no login", e)
            Result.failure(Exception("Erro de conexão: Verifique sua internet"))
        }
    }

    suspend fun cadastrarUsuario(user: User): Result<Unit> {
        return try {
            val response = api.cadastrar(user)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                val codigoErro = response.code()
                val erroBruto = response.errorBody()?.string() ?: ""
                val mensagemParaUsuario = when (codigoErro) {
                    500 -> "Erro interno no servidor: $erroBruto "
                    404 -> "Rota de cadastro não encontrada no servidor."
                    400 -> "Dados inválidos. Verifique os campos e tente novamente."
                    else -> "Erro desconhecido ($codigoErro): $erroBruto"
                }

                Result.failure(Exception(mensagemParaUsuario))
            }
        } catch (e: Exception) {
            Log.e("REPO", "Erro de rede", e)
            Result.failure(Exception("Falha na conexão com o servidor."))
        }
    }

    suspend fun listarUsuarios(): Result<List<User>> {
        return try {
            val response = api.listarUsuarios()

            if (response.isSuccessful) {
                val lista = response.body() ?: emptyList()
                Result.success(lista)
            } else {
                Result.failure(Exception("Erro ao buscar a lista de usuários"))
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Erro ao listar usuários", e)
            Result.failure(Exception("Erro de conexão: Verifique a sua internet"))
        }
    }

}