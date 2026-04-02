package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.abcgjl_smartcuisine_mobile.core.network.api.RetrofitInstance
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.response.LoginRequest
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    fun login(email: String, senha: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.authService.login(
                    LoginRequest(email, senha)
                )

                if (response.isSuccessful) {
                    onResult(true)
                } else {
                    onResult(false)
                }

            } catch (e: Exception) {
                onResult(false)
            }
        }
    }
}