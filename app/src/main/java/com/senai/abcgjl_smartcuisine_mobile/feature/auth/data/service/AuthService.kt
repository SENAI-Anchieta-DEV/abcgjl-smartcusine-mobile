package com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.service

import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.response.CadastroRequest
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.response.LoginRequest
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.response.LoginResponse
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @POST("usuarios")
    suspend fun cadastrar(
        @Body request: CadastroRequest
    ): Response<UserResponse>
}