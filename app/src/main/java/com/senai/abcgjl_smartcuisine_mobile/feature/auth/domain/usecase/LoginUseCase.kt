package com.senai.abcgjl_smartcuisine_mobile.feature.auth.domain.usecase

import com.senai.abcgjl_smartcuisine_mobile.feature.auth.domain.repository.AuthRepository

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val sessionManager: SessionManager
) {
    suspend operator fun invoke(email: String, password: String) {
        val session = authRepository.login(email = email, password = password)
        sessionManager.createSession(session.user)
    }
}