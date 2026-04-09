package com.senai.abcgjl_smartcuisine_mobile.core.network.api

import com.senai.abcgjl_smartcuisine_mobile.core.network.datastore.UserPreferences
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val userPreferences: UserPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val token = userPreferences.getToken()

        val request = chain.request().newBuilder()

        if (token.isNotEmpty()) {
            request.addHeader("Authorization", "Bearer $token")
        }

        return chain.proceed(request.build())
    }
}
