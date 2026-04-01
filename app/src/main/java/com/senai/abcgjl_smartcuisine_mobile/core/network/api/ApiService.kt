package com.senai.abcgjl_smartcuisine_mobile.core.network.api

import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("usuarios")
    suspend fun cadastrarUsuario(@Body user: User): Response<Void>
}