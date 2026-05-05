package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

@Composable
fun LoginHeader() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Carteirinha Digital",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Fase 3: login com persistência de sessão e cadastro inicial com aprovação pendente.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
