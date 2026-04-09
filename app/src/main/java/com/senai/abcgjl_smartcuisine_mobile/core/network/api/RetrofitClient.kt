package com.senai.abcgjl_smartcuisine_mobile.core.network.api

import com.senai.abcgjl_smartcuisine_mobile.core.network.datastore.UserPreferences
import okhttp3.OkHttpClient


object RetrofitClient {

    private const val BASE_URL = "http://10.0.2.2:8080/"

    fun getApi(userPreferences: UserPreferences): ApiService {

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(userPreferences))
            .build()

        return retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                retrofit2.converter.gson.GsonConverterFactory.create()
            )
            .build()
            .create(ApiService::class.java)
    }
}