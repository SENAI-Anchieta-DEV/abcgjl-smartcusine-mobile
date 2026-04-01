package com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.repository

import com.senai.abcgjl_smartcuisine_mobile.core.network.api.RetrofitClient
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.model.User

class UserRepository {

    suspend fun cadastrarUsuario(user: User): Result<Boolean> {
        return try {
            val response = RetrofitClient.api.cadastrarUsuario(user)

            if (response.isSuccessful) {
                Result.success(true)
            } else {
                Result.failure(Exception("Erro: ${response.code()}"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}