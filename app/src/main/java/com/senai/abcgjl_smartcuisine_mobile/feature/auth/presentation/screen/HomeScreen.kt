package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.navigation.Routes

@Composable
fun HomeScreen(
    navController: NavController
){
    Button(
        onClick = {navController.navigate(Routes.Home.route)},
        modifier = Modifier.fillMaxWidth(0.6f),
        shape = RoundedCornerShape(size = 9.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        border = BorderStroke(
            2.dp,
            MaterialTheme.colorScheme.primary
        )
    ) {
        Text("Home")
    }
}