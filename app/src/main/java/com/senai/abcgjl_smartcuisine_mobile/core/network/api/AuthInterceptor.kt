package com.senai.abcgjl_smartcuisine_mobile.core.network.api

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val authTokenProvider: AuthTokenProvider
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val path = originalRequest.url.encodedPath.lowercase()

        val isPublicRoute = path.contains("/login") ||
                path.contains("/register") ||
                path.contains("/signup") ||
                path.contains("/usuarios")

        if (isPublicRoute) {
            return chain.proceed(originalRequest)
        }

        val token = authTokenProvider.getToken()

        val authenticatedRequest = if (token.isNullOrBlank()) {
            originalRequest
        } else {
            originalRequest.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
        }

        return chain.proceed(authenticatedRequest)
    }
}