package com.senai.abcgjl_smartcuisine_mobile.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.SmartCuisineTheme

@Composable
fun LoginView(
    modifier: Modifier = Modifier,
    login: String,
    senha: String,
    onLoginChange: (String) -> Unit,
    onSenhaChange: (String) -> Unit,
    onLoginClick: () -> Unit
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        )
    ) {

        TextField(
            value = login,
            onValueChange = onLoginChange,
            label = { Text("Login:") }
        )

        TextField(
            value = senha,
            onValueChange = onSenhaChange,
            label = { Text("Senha:") }
        )

        Button(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth(0.6f),
            shape = RoundedCornerShape(9.dp)
        ) {
            Text("Entrar")
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginClaro(){
    SmartCuisineTheme(darkTheme = false){
        LoginView(
            login = "",
            senha = "",
            onLoginChange = {},
            onSenhaChange = {},
            onLoginClick = {},
            modifier = Modifier
                .fillMaxSize()
                .padding(50.dp)
        )
    }
}