package com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.remote

import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.remote.request.LoginRequestDto
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.remote.response.LoginResponseDTO
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.remote.response.MeResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApiService {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequestDto
    ): LoginResponseDTO

    @GET("auth/me")
    suspend fun getCurrentUser(
        @Header("Authorization") authorization: String
    ): MeResponseDto
}