package com.senai.abcgjl_smartcuisine_mobile.feature.signup.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.senai.abcgjl_smartcuisine_mobile.core.model.UserRole

@Composable
fun UserTypeSelector(
    selectedRole: UserRole?,
    onRoleSelected: (UserRole) -> Unit,
    errorMessage: String?
) {
    androidx.compose.foundation.layout.Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Tipo de usuário",
            style = MaterialTheme.typography.titleMedium
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FilterChip(
                selected = selectedRole == UserRole.ADMINISTRADOR, //VER DIREITO O USUARIO
                onClick = { onRoleSelected(UserRole.ADMINISTRADOR) },
                label = { Text("Aluno") },
                modifier = Modifier.weight(1f).heightIn(min = 48.dp)
            )
            FilterChip(
                selected = selectedRole == UserRole.GERENTE,//VER DIREITO O USUARIO
                onClick = { onRoleSelected(UserRole.GERENTE) },
                label = { Text("Professor") },
                modifier = Modifier.weight(1f).heightIn(min = 48.dp)
            )
        }
        errorMessage?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}