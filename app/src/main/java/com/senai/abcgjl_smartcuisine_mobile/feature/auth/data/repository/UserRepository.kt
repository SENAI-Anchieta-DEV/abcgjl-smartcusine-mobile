package com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.repository

import com.senai.abcgjl_smartcuisine_mobile.core.network.api.RetrofitClient
import com.senai.abcgjl_smartcuisine_mobile.core.network.datastore.UserPreferences
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.model.User
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.response.LoginRequest
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.response.LoginResponse

class UserRepository(
    private val userPreferences: UserPreferences
) {
    private val api = RetrofitClient.getApi(userPreferences)

    suspend fun cadastrarUsuario(user: User): Result<Boolean> {
        return try {
            val response = api.cadastrarUsuario(user)

            if (response.isSuccessful) {
                Result.success(true)
            } else {
                Result.failure(
                    Exception(
                        when (response.code()) {
                            400 -> "Dados inválidos"
                            401 -> "Não autorizado"
                            500 -> "Erro no servidor"
                            else -> "Erro desconhecido"
                        }
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(Exception("Erro de conexão"))
        }
    }

    suspend fun login(email: String, senha: String): Result<LoginResponse> {
        return try {

            val response = api.login(LoginRequest(email, senha))

            if (response.isSuccessful && response.body() != null) {
                val loginResponse = response.body()!!
                userPreferences.saveToken(loginResponse.token)
                Result.success(loginResponse)
            } else {
                Result.failure(
                    Exception(
                        when (response.code()) {
                            400 -> "Credenciais inválidas"
                            401 -> "Email ou senha incorretos"
                            500 -> "Erro no servidor"
                            else -> "Erro desconhecido"
                        }
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(Exception("Erro de conexão"))
        }
    }
}