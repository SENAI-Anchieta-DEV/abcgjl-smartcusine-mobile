package com.senai.abcgjl_smartcuisine_mobile.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.senai.abcgjl_smartcuisine_mobile.R

@Composable
fun LoginView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center // Centraliza o conjunto todo
    ) {
        // O Logo fica FORA da caixa branca (flutuando)
        Image(
            painter = painterResource(id = R.drawable.smartcuisinelogo),
            contentDescription = "Logo smartcuisine",
            modifier = Modifier
                .size(190.dp)
                .padding(bottom = 25.dp)
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Seu email", color = Color.Gray) },
                    shape = RoundedCornerShape(50.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFEBEBEB),
                        unfocusedContainerColor = Color(0xFFEBEBEB),
                        cursorColor = Color(0xFFE88C45)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Senha", color = Color.Gray) },
                    shape = RoundedCornerShape(50.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFEBEBEB),
                        unfocusedContainerColor = Color(0xFFEBEBEB),
                        cursorColor = Color(0xFFE88C45)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE88C45))
                ) {
                    Text("Entrar", color = Color.White)
                }
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEBEBEB))
                ) {
                    Text("Cadastre-se", color = Color.DarkGray)
                }
            }
        }
    }
}