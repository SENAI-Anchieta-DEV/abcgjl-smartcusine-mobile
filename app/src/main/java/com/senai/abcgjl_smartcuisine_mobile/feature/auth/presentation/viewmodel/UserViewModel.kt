package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel

import androidx.lifecycle.*
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.model.User
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: UserRepository
) : ViewModel() {
    private val _resultado = MutableLiveData<String>()
    val resultado: LiveData<String> = _resultado

    fun cadastrar(user: User) {
        viewModelScope.launch {
            val result = repository.cadastrarUsuario(user)

            if (result.isSuccess) {
                _resultado.value = "Cadastro realizado com sucesso!"
            } else {
                _resultado.value = "Erro: ${result.exceptionOrNull()?.message}"
            }
        }
    }
}