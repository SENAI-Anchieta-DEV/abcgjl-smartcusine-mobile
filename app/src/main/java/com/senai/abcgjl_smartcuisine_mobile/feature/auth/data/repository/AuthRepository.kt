package com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.repository

import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.response.CadastroRequest
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.service.AuthService

class AuthRepository(private val api: AuthService) {
    suspend fun cadastrar(request: CadastroRequest) = api.cadastrar(request)
}