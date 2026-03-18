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
fun LoginView(modifier: Modifier = Modifier){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        )

    ){

        TextField(
            value = "",
            onValueChange = {},
            label = {
                Text("Login: ")
            },

            )

        Button(
            onClick = {},
            Modifier.fillMaxWidth(.9f), shape = RoundedCornerShape(size = 4.dp)
        ){
            Text("Entrar")
        }
    }
}



@Preview(
    showBackground = true,
    showSystemUi = true
)

@Composable
fun PreviewLoginClaro(){
    SmartCuisineTheme(darkTheme = false){
        LoginView(modifier = Modifier.fillMaxSize().padding(50.dp))
    }
}
@Preview(
    showBackground = true,
    showSystemUi = true
)

@Composable
fun PreviewLoginEscuro(){
    SmartCuisineTheme(darkTheme = true) {
        LoginView(modifier = Modifier.fillMaxSize().padding(50.dp))
    }

}