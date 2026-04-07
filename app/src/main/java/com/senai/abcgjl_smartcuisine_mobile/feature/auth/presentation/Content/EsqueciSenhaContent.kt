package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.Content

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senai.abcgjl_smartcuisine_mobile.R
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.SmartCuisineTheme
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.ui.components.background.AuthGradientBackground

@Composable
fun EsqueciSenhaContent(
    modifier: Modifier = Modifier,
    email: String = "",
    onEmailChange: (String) -> Unit = {},
    onSendClick: () -> Unit = {},
    onVoltarClick: () -> Unit = {}
) {

    val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    Box(modifier = modifier.fillMaxSize()) {

        AuthGradientBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.logosmartcuisine),
                contentDescription = "logo smartcuisine",
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Surface(
                shape = RoundedCornerShape(24.dp),
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp,
                shadowElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier.padding(22.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Recuperar senha",
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Digite seu email para redefinir sua senha.",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    TextField(
                        value = email,
                        onValueChange = onEmailChange,
                        placeholder = { Text("Seu email") },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Email,
                                contentDescription = "Email",
                                tint = Color.Gray
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = onSendClick,
                        enabled = isValidEmail,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary // ✅ theme
                        )
                    ) {
                        Text("Enviar link", color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    TextButton(onClick = onVoltarClick) {
                        Text(
                            "Voltar para login",
                            color = MaterialTheme.colorScheme.primary // ✅ theme
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewEsqueciSenhaClaro() {
    SmartCuisineTheme(darkTheme = false) {
        EsqueciSenhaContent()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewEsqueciSenhaEscuro() {
    SmartCuisineTheme(darkTheme = true) {
        EsqueciSenhaContent()
    }
}