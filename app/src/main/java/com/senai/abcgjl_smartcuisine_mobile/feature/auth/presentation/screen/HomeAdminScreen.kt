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
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel.SensorViewModel

// HomeAdminScreen.kt
@Composable
fun HomeAdminScreen(
    navController: NavController,
    sessionViewModel: SessionViewModel,
    sensorViewModel: SensorViewModel = hiltViewModel()
) {
    val sessionUser by sessionViewModel.sessionUser.collectAsState()

    // 2. Coleta o StateFlow dos sensores em tempo real
    val sensores by sensorViewModel.sensores.collectAsState()

    val stats = DashboardStats(11, 2, 6, 2, 1)

    Scaffold(
        bottomBar = { AdminBottomSummary(stats) }
    ) { paddingValues ->
        HomeAdminContent(
            paddingValues = paddingValues,
            stats = stats,
            sensores = sensores,
            sessionUser = sessionUser,
            onAddProductClick = { println("Clicou em Adicionar Produto") },
            onReportsClick = { println("Clicou em Relatórios") },
            onLogout = { sessionViewModel.logout() }
        )
    }
}