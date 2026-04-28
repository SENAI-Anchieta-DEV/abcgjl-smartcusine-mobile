package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.screen

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.Content.AdminBottomSummary
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.Content.HomeAdminContent
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel.DashboardStats
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel.SensorData

@Composable
fun HomeAdminScreen(navController: NavController) {
    // Simulando dados que viriam do ViewModel/Backend
    val stats = DashboardStats(11, 2, 6, 2, 1)

    val sensores = listOf(
        SensorData("Freezer 1", "-13°C", "Normal", "Ideal: -10 à -15°C", false),
        SensorData("Forno 1", "190°C", "Normal", "Ideal: 180 à 200°C", true)
    )

    Scaffold(
        bottomBar = { AdminBottomSummary(stats) }
    ) { paddingValues ->
        HomeAdminContent(
            paddingValues = paddingValues,
            stats = stats, // Adicionei o stats aqui pois o Content precisa dele para o rodapé se você o moveu para lá
            sensores = sensores,
            onAddProductClick = {
                // Quando você criar a tela de cadastro de produto, use:
                // navController.navigate("cadastro_produto")
                println("Clicou em Adicionar Produto")
            },
            onReportsClick = {
                // Quando você criar a tela de relatórios, use:
                // navController.navigate("relatorios")
                println("Clicou em Relatórios")
            }
        )
    }
}