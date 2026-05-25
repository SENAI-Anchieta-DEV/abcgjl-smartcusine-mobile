package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.senai.abcgjl_smartcuisine_mobile.core.network.api.RetrofitClient
import com.senai.abcgjl_smartcuisine_mobile.core.datastore.UserPreferences
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.repository.UserRepository
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.domain.repository.AuthRepository

class CadastroViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CadastroViewModel::class.java)) {
            val userPreferences = UserPreferences(context)
            val api = RetrofitClient.getApi(userPreferences)
            val repository = UserRepository(api = api, userPreferences = userPreferences)

            @Suppress("UNCHECKED_CAST")
            return CadastroViewModel(repository) as T
        }

        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            val userPreferences = UserPreferences(context)
            val api = RetrofitClient.getApi(userPreferences)
            val repository = UserRepository(api = api, userPreferences = userPreferences)

            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository) as T
        }

        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            val userPreferences = UserPreferences(context)
            val api = RetrofitClient.getApi(userPreferences)
            val repository = UserRepository(api = api, userPreferences = userPreferences)

        }

        throw IllegalArgumentException("ViewModel desconhecido")
    }
}
