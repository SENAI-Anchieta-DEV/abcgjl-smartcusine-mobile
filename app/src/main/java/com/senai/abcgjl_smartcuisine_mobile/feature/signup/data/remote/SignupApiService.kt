package com.senai.abcgjl_smartcuisine_mobile.feature.signup.data.remote

import com.senai.abcgjl_smartcuisine_mobile.feature.signup.data.remote.request.SignupRequestDto
import com.senai.abcgjl_smartcuisine_mobile.feature.signup.data.remote.response.SignupResponseDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface SignupApiService {
    @POST("usuarios")
    suspend fun register(
        @Body request: SignupRequestDto
    ): SignupResponseDto
}