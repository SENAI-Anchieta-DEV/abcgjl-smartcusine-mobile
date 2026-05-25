package com.senai.abcgjl_smartcuisine_mobile.feature.signup.domain.repository

import com.senai.abcgjl_smartcuisine_mobile.feature.signup.domain.model.SignupForm
import com.senai.abcgjl_smartcuisine_mobile.feature.signup.domain.model.SignupResult

interface SignupRepository {
    suspend fun register(form: SignupForm): SignupResult
}
