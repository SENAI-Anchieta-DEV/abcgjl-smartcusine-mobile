package com.senai.abcgjl_smartcuisine_mobile.core.network.api

import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.model.User
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.remote.response.LoginResponseDTO
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.remote.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("usuarios")
    suspend fun cadastrar(@Body user: User): Response<UserResponse>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponseDTO>

    @GET("usuarios")
    suspend fun listarUsuarios(): Response<List<User>>
}