package com.senai.abcgjl_smartcuisine_mobile.core.network.api

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthTokenProvider @Inject constructor() {
    @Volatile
    private var currentToken: String? = null

    fun updateToken(token: String?) {
        currentToken = token?.takeIf { it.isNotBlank() }
    }

    fun getToken(): String? = currentToken
}