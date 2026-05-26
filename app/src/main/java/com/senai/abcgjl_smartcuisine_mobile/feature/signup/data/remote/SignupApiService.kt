package com.senai.abcgjl_smartcuisine_mobile.feature.signup.data.remote

import com.senai.abcgjl_smartcuisine_mobile.feature.signup.data.remote.response.SignupResponseDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface SignupApiService {

    @Multipart
    @POST("usuarios/cadastro")
    suspend fun register(
        @Part("nome") nome: RequestBody,
        @Part("email") email: RequestBody,
        @Part("senha") senha: RequestBody,
        @Part("tipoUsuario") role: RequestBody
    ): SignupResponseDto
}