package com.senai.abcgjl_smartcuisine_mobile.feature.auth.domain.repository

import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.remote.response.CadastroRequest
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.domain.model.AuthSession

interface AuthRepository {
    suspend fun login(email: String, password: String): AuthSession
    suspend fun cadastrar(request: CadastroRequest)
}