package com.senai.abcgjl_smartcuisine_mobile.core.network.api

import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.model.User
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.response.LoginRequest
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("usuarios")
    suspend fun cadastrarUsuario(@Body user: User): Response<Void>

        @POST("login")
        suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}