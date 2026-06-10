package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.screen

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.senai.abcgjl_smartcuisine_mobile.core.session.SessionViewModel
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.Content.AdminBottomSummary
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.Content.HomeAdminContent
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel.DashboardStats
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel.SensorData

// HomeAdminScreen.kt
@Composable
fun HomeAdminScreen(
    navController: NavController,
    sessionViewModel: SessionViewModel
) {
    val sessionUser by sessionViewModel.sessionUser.collectAsState()

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
            stats = stats,
            sensores = sensores,
            sessionUser = sessionUser, // ← adicionar
            onAddProductClick = { println("Clicou em Adicionar Produto") },
            onReportsClick = { println("Clicou em Relatórios") },
            onLogout = { sessionViewModel.logout() }
        )
    }
}