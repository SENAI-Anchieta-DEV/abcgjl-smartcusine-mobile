package com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.service

import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.response.CadastroRequest
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.response.LoginRequest
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @POST("auth/cadastrar") // <-- Ajuste aqui se a URL da sua API for diferente
    suspend fun cadastrar(
        @Body request: CadastroRequest
    ): Response<LoginResponse>
}