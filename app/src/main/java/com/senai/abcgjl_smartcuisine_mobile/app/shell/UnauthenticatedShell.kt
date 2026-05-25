package com.senai.abcgjl_smartcuisine_mobile.app.shell

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun UnauthenticatedShell(
    title: String,
    navigationIconType: NavigationIconType = NavigationIconType.NONE,
    onNavigationClick: (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar(
                config = AppBarConfig(
                    title = title,
                    navigationIconType = navigationIconType
                ),
                onNavigationClick = onNavigationClick
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            content(innerPadding)
        }
    }
}