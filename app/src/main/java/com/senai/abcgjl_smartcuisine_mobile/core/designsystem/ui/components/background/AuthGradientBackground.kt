package com.senai.abcgjl_smartcuisine_mobile.core.designsystem.ui.components.background

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun AuthGradientBackground() {
    Box(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFBFA2FF).copy(alpha = 0.6f),
                            Color.Transparent
                        ),
                        center = Offset(200f, 1400f),
                        radius = 1000f
                    )
                )
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFAEDCFF).copy(alpha = 0.6f),
                            Color.Transparent
                        ),
                        center = Offset(800f, 200f),
                        radius = 1000f
                    )
                )
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFEB863A).copy(alpha = 0.6f),
                            Color.Transparent
                        ),
                        center = Offset(1000f, 800f),
                        radius = 1000f
                    )
                )
        )
    }
}