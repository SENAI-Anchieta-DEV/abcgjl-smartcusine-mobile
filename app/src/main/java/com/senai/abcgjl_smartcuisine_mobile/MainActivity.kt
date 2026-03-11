package com.senai.abcgjl_smartcuisine_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.senai.abcgjl_smartcuisine_mobile.ui.theme.LoginView
import com.senai.abcgjl_smartcuisine_mobile.ui.theme.SmartCuisineTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartCuisineTheme {

                Box(modifier = Modifier.fillMaxSize()) {

                    Image(
                        painter = painterResource(id = R.drawable.aquarelafundo),
                        contentDescription = "aquarela do fundo",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop)

                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Transparent
                    ) {
                        LoginView(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 32.dp)
                        )
                    }
                }
            }
        }
    }
}