package com.senai.abcgjl_smartcuisine_mobile.core.network.api

object RetrofitClient {

    private const val BASE_URL = "http://10.0.2.2:8080/"

    val api: ApiService by lazy {
        retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                retrofit2.converter.gson.GsonConverterFactory.create()
            )
            .build()
            .create(ApiService::class.java)
    }
}