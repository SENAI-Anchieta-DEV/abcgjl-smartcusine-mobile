package com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.repository



import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.remote.AuthApiService
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.remote.request.LoginRequestDto

class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService
) : AuthRepository {

    override suspend fun login(email: String, password: String): AuthSession {
        return try {
            val loginResponse = authApiService.login(
                LoginRequestDto(email = email, password = password)
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
                    id = meResponse.id.orEmpty(),
                    name = meResponse.name.orEmpty(),
                    email = meResponse.email.orEmpty(),
                    role = userRole,
                    authToken = token,
                    photoUrl = meResponse.photoUrl,
                    enrollment = meResponse.enrollment,
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