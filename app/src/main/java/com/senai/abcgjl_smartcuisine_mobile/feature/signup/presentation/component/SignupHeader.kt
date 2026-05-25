package com.senai.abcgjl_smartcuisine_mobile.feature.signup.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun SignupHeader() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Criar cadastro",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Escolha o tipo de usuário, preencha os dados básicos e envie a solicitação para aprovação.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}