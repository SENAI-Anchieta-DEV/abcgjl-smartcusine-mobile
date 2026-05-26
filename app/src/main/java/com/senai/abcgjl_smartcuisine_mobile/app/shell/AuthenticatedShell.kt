package com.senai.abcgjl_smartcuisine_mobile.app.shell

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.senai.abcgjl_smartcuisine_mobile.core.session.SessionViewModel
import com.senai.abcgjl_smartcuisine_mobile.core.model.ThemeMode
import kotlinx.coroutines.launch

@Composable
fun AuthenticatedShell(
    title: String,
    sessionViewModel: SessionViewModel,
    onLogout: () -> Unit,
    navigationIconType: NavigationIconType = NavigationIconType.MENU,
    onNavigationClick: (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val themeMode by sessionViewModel.themeMode.collectAsStateWithLifecycle()
    val sessionUser by sessionViewModel.sessionUser.collectAsStateWithLifecycle()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                sessionUser = sessionUser,
                isDarkTheme = themeMode == ThemeMode.DARK,
                onToggleTheme = { sessionViewModel.toggleTheme() },
                onLogout = onLogout
            )
        }
    ) {
        Scaffold(
            topBar = {
                AppTopBar(
                    config = AppBarConfig(
                        title = title,
                        navigationIconType = navigationIconType
                    ),
                    onNavigationClick = {
                        when (navigationIconType) {
                            NavigationIconType.MENU -> {
                                scope.launch { drawerState.open() }
                            }
                            NavigationIconType.BACK -> onNavigationClick?.invoke()
                            NavigationIconType.NONE -> Unit
                        }
                    }
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize()) {
                content(innerPadding)
            }
        }
    }
}