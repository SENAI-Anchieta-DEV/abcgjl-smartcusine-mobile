package com.senai.abcgjl_smartcuisine_mobile.feature.signup.domain.usecase

import com.senai.abcgjl_smartcuisine_mobile.feature.signup.domain.model.SignupForm
import com.senai.abcgjl_smartcuisine_mobile.feature.signup.domain.repository.SignupRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val signupRepository: SignupRepository
) {
    suspend operator fun invoke(form: SignupForm) = signupRepository.register(form)
}