package com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.repository

import com.senai.abcgjl_smartcuisine_mobile.core.model.SessionUser
import com.senai.abcgjl_smartcuisine_mobile.core.model.UserRole
import com.senai.abcgjl_smartcuisine_mobile.core.network.api.ApiExceptionParser
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.remote.AuthApiService
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.remote.request.LoginRequestDto
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.domain.model.AuthSession
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.domain.repository.AuthRepository
import jakarta.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService
) : AuthRepository {

    override suspend fun login(email: String, password: String): AuthSession {
        return try {
            val loginResponse = authApiService.login(
                LoginRequestDto(email = email, senha = password)
            )

            val token = loginResponse.accessToken.trim()
            require(token.isNotBlank()) {
                "A API retornou um token vazio no login."
            }

            val meResponse = authApiService.getCurrentUser(
                authorization = "Bearer $token"
            )

            val userRole = UserRole.fromApiValue(meResponse.role)
                ?: throw IllegalStateException(
                    "O usuário autenticado não possui tipo definido na API. Ajuste o cadastro antes de entrar no app."
                )

            AuthSession(
                user = SessionUser(
                    name = meResponse.name.orEmpty(),
                    email = meResponse.email.orEmpty(),
                    role = userRole,
                    authToken = token,
                )
            )
        } catch (throwable: Throwable) {
            throw IllegalStateException(
                ApiExceptionParser.toMessage(
                    throwable = throwable,
                    defaultMessage = "Não foi possível realizar o login na API."
                )
            )
        }
    }
}