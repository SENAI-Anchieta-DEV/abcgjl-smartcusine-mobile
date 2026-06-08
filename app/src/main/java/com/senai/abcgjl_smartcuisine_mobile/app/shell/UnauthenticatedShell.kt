 package com.senai.abcgjl_smartcuisine_mobile.app.shell

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp // 🚀 Import necessário para o 0.dp

@Composable
fun UnauthenticatedShell(
    title: String,
    showHeader: Boolean = true,
    navigationIconType: NavigationIconType = NavigationIconType.NONE,
    onNavigationClick: (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            if (showHeader) {
                AppTopBar(
                    config = AppBarConfig(
                        title = title,
                        navigationIconType = navigationIconType
                    ),
                    onNavigationClick = onNavigationClick
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            val adjustedPadding = if (showHeader) {
                innerPadding
            } else {
                PaddingValues(
                    top = 0.dp,
                    bottom = innerPadding.calculateBottomPadding()
                )
            }
            content(adjustedPadding)
        }
    }
}