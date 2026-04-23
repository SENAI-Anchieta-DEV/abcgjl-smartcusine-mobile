package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.Content

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.SmartCuisineTheme
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel.DashboardStats
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel.SensorData

@Composable
fun HomeAdminContent(
    paddingValues: PaddingValues,
    stats: DashboardStats,
    sensores: List<SensorData>,
    onAddProductClick: () -> Unit,
    onReportsClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB0C4DE))
            .padding(paddingValues)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { Spacer(modifier = Modifier.height(16.dp)) }

        // 1. Cabeçalho
        item { AdminHeader(onReportsClick, onAddProductClick) }

        item { Spacer(modifier = Modifier.height(24.dp)) }

        // 2. Alertas
        item { AlertCard("Alimento Vencido", "Um item vencido precisa ser removido", Color.Red) }
        item { Spacer(modifier = Modifier.height(12.dp)) }
        item { AlertCard("Atenção: Validade próxima", "Itens vencem em 5 dias", Color(0xFFE6863B)) }

        item { Spacer(modifier = Modifier.height(24.dp)) }

        // 3. Monitoramento
        item {
            Text(
                "Monitoramento de temperatura",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6A7B8A)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        items(sensores) { sensor ->
            TemperatureCard(
                local = sensor.nomeEquipamento,
                temp = sensor.temperaturaAtual,
                status = sensor.status,
                ideal = sensor.faixaIdeal,
                accentColor = if (sensor.isAlerta) Color(0xFFE6863B) else Color(0xFF6A7B8A)
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        item { Spacer(modifier = Modifier.height(32.dp)) }
    }
}

// --- FUNÇÕES AUXILIARES (COMPONENTES) ---

@Composable
fun AdminHeader(onReportsClick: () -> Unit, onAddProductClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Logo Placeholder
        Icon(Icons.Default.RestaurantMenu, contentDescription = null, tint = Color(0xFFE6863B), modifier = Modifier.size(40.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = onReportsClick, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A7B8A)), shape = RoundedCornerShape(8.dp)) {
                Text("Relatórios Semanais", fontSize = 10.sp)
            }
            Button(onClick = onAddProductClick, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE6863B)), shape = RoundedCornerShape(8.dp)) {
                Text("+ Adicionar Produto", fontSize = 10.sp)
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Default.AccountCircle, contentDescription = null, modifier = Modifier.size(45.dp), tint = Color(0xFF6A7B8A))
            Text("Bem-Vindo,", fontSize = 10.sp)
            Text("Administrador", fontWeight = FontWeight.Bold, fontSize = 10.sp)
        }
    }
}

@Composable
fun AlertCard(title: String, subtitle: String, color: Color) {
    Surface(shape = RoundedCornerShape(20.dp), color = Color.White, modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Info, contentDescription = null, tint = color, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(title, color = color, fontWeight = FontWeight.Bold)
                Text(subtitle, fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun TemperatureCard(local: String, temp: String, status: String, ideal: String, accentColor: Color) {
    Surface(shape = RoundedCornerShape(24.dp), color = Color.White, modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text(local, color = Color.Gray, fontSize = 14.sp)
                Text(ideal, color = Color.LightGray, fontSize = 11.sp)
                Text(temp, fontSize = 32.sp, fontWeight = FontWeight.Bold)
                Text(status, color = Color.Gray)
            }
            Icon(Icons.Default.DeviceThermostat, contentDescription = null, tint = accentColor, modifier = Modifier.size(60.dp))
        }
    }
}

@Composable
fun AdminBottomSummary(stats: DashboardStats) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Row(modifier = Modifier.padding(20.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            StatItem("Total de Itens", stats.totalItens.toString())
            StatItem("Em preparo", stats.emPreparo.toString())
            StatItem("Prontos.", stats.prontos.toString())
            StatItem("Validade crítica", stats.validadeCritica.toString())
            StatItem("Vencidos", stats.vencidos.toString())
        }
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontWeight = FontWeight.Bold)
        Text(label, fontSize = 9.sp, color = Color.Gray, textAlign = TextAlign.Center)
    }
}
