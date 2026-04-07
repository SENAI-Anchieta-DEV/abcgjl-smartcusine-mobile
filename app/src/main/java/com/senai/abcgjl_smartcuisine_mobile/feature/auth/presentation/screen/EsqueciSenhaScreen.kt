package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.Content.EsqueciSenhaContent

@Composable
fun EsqueciSenhaScreen(
    navController: NavHostController
) {
    var email by remember { mutableStateOf("") }

    EsqueciSenhaContent(
        email = email,
        onEmailChange = { email = it },

        onSendClick = {
            // 🔥  integrar com Firebase depois
            println("Enviar recuperação para: $email")
        },

        onVoltarClick = {
            navController.popBackStack()
        }
    )
}