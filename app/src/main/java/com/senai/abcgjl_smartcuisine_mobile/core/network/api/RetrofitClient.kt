package com.senai.abcgjl_smartcuisine_mobile.core.network.api

import com.senai.abcgjl_smartcuisine_mobile.core.network.datastore.UserPreferences
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


object RetrofitClient {

    private const val BASE_URL = "https://abcgjl-smartcusine-backend-api.onrender.com/"

    fun getApi(userPreferences: UserPreferences): ApiService {

        // Adiciona logs no console para vermos o JSON entrando e saindo
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(userPreferences))
            .addInterceptor(logging)
            // Configurações cruciais para o plano gratuito do Render:
            .connectTimeout(60, TimeUnit.SECONDS) // Espera o servidor "acordar"
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        return retrofit2.Retrofit.Builder()
            .baseUrl("https://abcgjl-smartcusine-backend-api.onrender.com/")
            .client(okHttpClient)
            .addConverterFactory(
                retrofit2.converter.gson.GsonConverterFactory.create()
            )
            .build()
            .create(ApiService::class.java)
    }
}