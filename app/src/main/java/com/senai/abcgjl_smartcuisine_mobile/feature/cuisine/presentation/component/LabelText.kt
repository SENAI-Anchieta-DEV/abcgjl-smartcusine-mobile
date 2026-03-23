package com.senai.abcgjl_smartcuisine_mobile.feature.cuisine.presentation.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun LabelText(
    label: String,
    modifier: Modifier = Modifier
){
    Text(
        text = label,
        textAlign = TextAlign.Right,
        style = MaterialTheme.typography.bodyLarge,
        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
        modifier = modifier,
        color = MaterialTheme.colorScheme.onSurface

    )
}