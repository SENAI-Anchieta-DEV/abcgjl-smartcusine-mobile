package com.senai.abcgjl_smartcuisine_mobile.feature.cuisine.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun ValueText(
    value: String,
    fontSize: TextUnit = MaterialTheme.typography.titleLarge.fontSize,
    fontWeight: FontWeight = MaterialTheme.typography.titleLarge.fontWeight ?: FontWeight.Normal,
    modifier: Modifier = Modifier ){

    Text(
        text = value,
        modifier = modifier.padding(start = 8.dp),
        fontSize = fontSize,
        fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
        lineHeight = MaterialTheme.typography.titleLarge.lineHeight,
        fontWeight = fontWeight,
        softWrap = true,
        color = MaterialTheme.colorScheme.onSurface
    )
}