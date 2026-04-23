package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel

// Dados das estatísticas do rodapé
data class DashboardStats(
    val totalItens: Int = 0,
    val emPreparo: Int = 0,
    val prontos: Int = 0,
    val validadeCritica: Int = 0,
    val vencidos: Int = 0
)

// Dados para cada Card de Temperatura (vêm do ESP32 no futuro)
data class SensorData(
    val nomeEquipamento: String,
    val temperaturaAtual: String,
    val status: String,
    val faixaIdeal: String,
    val isAlerta: Boolean = false
)