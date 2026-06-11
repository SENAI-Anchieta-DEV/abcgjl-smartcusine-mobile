package com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.repository

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

            AuthSession(
                user = SessionUser(
                    nome = "",
                    email = email,
                    role = UserRole.ADMIN,
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

    override suspend fun cadastrar(request: CadastroRequest) {
        TODO("Not yet implemented")
    }
}