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

    suspend fun fazerLogin(email: String, senha: String): Result<Unit> {
        return try {
            val request = LoginRequest(email, senha)
            val response = api.login(request)

            if (response.isSuccessful) {
                val loginResponse = response.body()

                if (loginResponse != null && loginResponse.success) {
                    userPreferences.saveToken(loginResponse.token)
                    Result.success(Unit)
                } else {
                    Result.failure(Exception(loginResponse?.message ?: "Erro ao fazer login"))
                }
            } else {
                Result.failure(Exception("Email ou senha incorretos"))
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Erro no login", e)
            Result.failure(Exception("Erro de conexão: Verifique a sua internet"))
        }
    }

    suspend fun cadastrarUsuario(user: User): Result<Unit> {
        return try {
            val response = api.cadastrarUsuario(user)

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Erro ao cadastrar. Verifique os dados ou tente outro email."))
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Erro no cadastro", e)
            Result.failure(Exception("Erro de conexão: Verifique a sua internet"))
        }
    }

    suspend fun listarUsuarios(): Result<List<User>> {
        return try {
            val response = api.listarUsuarios()

            if (response.isSuccessful) {
                // Se der sucesso, pegamos a lista do corpo da resposta.
                // Se vier nula, devolvemos uma lista vazia (emptyList)
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