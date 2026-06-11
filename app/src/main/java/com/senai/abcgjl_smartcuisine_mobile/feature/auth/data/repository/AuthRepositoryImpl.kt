package com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.repository

import android.util.Base64
import com.senai.abcgjl_smartcuisine_mobile.core.common.network.ApiExceptionParser
import com.senai.abcgjl_smartcuisine_mobile.core.model.ApprovalStatus
import com.senai.abcgjl_smartcuisine_mobile.core.model.SessionUser
import com.senai.abcgjl_smartcuisine_mobile.core.model.UserRole
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.remote.AuthApiService
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.remote.request.LoginRequestDto
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.remote.response.CadastroRequest
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.domain.model.AuthSession
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService
) : AuthRepository {

    override suspend fun login(
        email: String,
        senha: String
    ): AuthSession {

        return try {

            val loginResponse = authApiService.login(
                LoginRequestDto(
                    email = email,
                    senha = senha
                )
            )

            val token = loginResponse.accessToken.trim()

            require(token.isNotBlank()) {
                "A API retornou um token vazio no login."
            }

            return AuthSession(
                user = SessionUser(
                    email = email,
                    role = extractRoleFromJwt(token),
                    authToken = token,
                    approvalStatus = ApprovalStatus.APROVADO
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

    private fun extractRoleFromJwt(token: String): UserRole? {
        return try {

            val payload = token.split(".")[1]

            val decoded = Base64.decode(
                payload,
                Base64.URL_SAFE or Base64.NO_WRAP
            )

            val json = String(decoded)

            when {
                json.contains("\"role\":\"ADMIN\"") -> UserRole.ADMIN
                json.contains("\"role\":\"GERENTE\"") -> UserRole.GERENTE
                json.contains("\"role\":\"COZINHEIRO\"") -> UserRole.COZINHEIRO
                else -> null
            }

        } catch (e: Exception) {
            null
        }
    }

    override suspend fun cadastrar(request: CadastroRequest) {
        TODO("Not yet implemented")
    }
}