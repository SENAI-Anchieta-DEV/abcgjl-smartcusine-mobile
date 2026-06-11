package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.repository.SensorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SensorViewModel @Inject constructor(
    private val repository: SensorRepository
) : ViewModel() {

    val sensores = repository.sensores

    init {
        repository.iniciar()
    }
}