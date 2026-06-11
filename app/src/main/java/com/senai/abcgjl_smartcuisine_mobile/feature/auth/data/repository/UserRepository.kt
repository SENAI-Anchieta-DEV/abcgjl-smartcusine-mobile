package com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.repository


import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.model.User
import android.util.Log
import com.senai.abcgjl_smartcuisine_mobile.core.network.api.ApiService
import com.senai.abcgjl_smartcuisine_mobile.core.datastore.UserPreferences
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.remote.request.LoginRequestDto

class UserRepository(
    private val api: ApiService
    ,
    private val userPreferences: UserPreferences
) {

    suspend fun fazerLogin(email: String, senha: String): Result<User> {
        return try {
            val request = LoginRequestDto(email, senha)
            val response = api.login(request)

            if (response.isSuccessful && response.body() != null) {
                val loginResponse = response.body()!!

                // Salvamos o token
                userPreferences.saveToken(loginResponse.accessToken)

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

            Log.d("CADASTRO", "Enviando: $user")

            val response = api.cadastrar(user)

            Log.d("CADASTRO", "Código: ${response.code()}")
            Log.d("CADASTRO", "Code: ${response.code()}")
            Log.d("CADASTRO", "Success: ${response.isSuccessful}")
            Log.d("CADASTRO", "Erro: ${response.errorBody()?.string()}")

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(
                    Exception("Erro ${response.code()}")
                )
            }

        } catch (e: Exception) {
            Log.e("CADASTRO", "EXCEPTION", e)
            Result.failure(e)
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