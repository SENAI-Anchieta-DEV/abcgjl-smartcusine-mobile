package com.senai.abcgjl_smartcuisine_mobile.core.designsystem.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.senai.abcgjl_smartcuisine_mobile.app.shell.NavigationIconType
import com.senai.abcgjl_smartcuisine_mobile.app.shell.UnauthenticatedShell
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.component.LoadingState
import com.senai.abcgjl_smartcuisine_mobile.core.model.SessionViewModel
import com.senai.abcgjl_smartcuisine_mobile.core.model.UserRole
import com.senai.abcgjl_smartcuisine_mobile.core.session.SessionState
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.screen.LoginScreen
import com.senai.abcgjl_smartcuisine_mobile.feature.signup.presentation.screen.SignupScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    sessionViewModel: SessionViewModel = hiltViewModel()
) {
    val sessionState = sessionViewModel.sessionState.collectAsStateWithLifecycle().value
    val sessionUser = sessionViewModel.sessionUser.collectAsStateWithLifecycle().value

    fun authenticatedRouteForCurrentUser(): String {
        return when (sessionUser.role) {
            UserRole.ADMINISTRADOR -> AppDestinations.AdmHome.route
            UserRole.GERENTE -> AppDestinations.GerenteHome.route
            UserRole.COZINHEIRO -> AppDestinations.CozinheiroHome.route
            null -> AppDestinations.Login.route
        }
    }

    LaunchedEffect(sessionState, sessionUser.role) {
        when (sessionState) {
            SessionState.Authenticated -> {
                val destination = authenticatedRouteForCurrentUser()
                if (destination != AppDestinations.Login.route) {
                    navController.navigate(destination) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            }
            SessionState.Unauthenticated -> {
                navController.navigate(AppDestinations.Login.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
            SessionState.Loading -> Unit
        }
    }

    NavHost(
        navController = navController,
        startDestination = AppDestinations.Login.route
    ) {
        composable(AppDestinations.Login.route) {
            if (sessionState == SessionState.Loading) {
                LoadingState(message = "Restaurando a sessão...")
            } else {
                UnauthenticatedShell(title = "Entrar") {
                    LoginScreen(
                        innerPadding = it,
                        onLoginSuccess = {
                            navController.navigate(authenticatedRouteForCurrentUser()) {
                                popUpTo(AppDestinations.Login.route) { inclusive = true }
                            }
                        },
                        onNavigateToSignup = { navController.navigate(AppDestinations.Signup.route) }
                    )
                }
            }
        }

        composable(AppDestinations.Signup.route) {
            UnauthenticatedShell(
                title = "Cadastro",
                navigationIconType = NavigationIconType.BACK,
                onNavigationClick = { navController.popBackStack() }
            ) { innerPadding ->
                SignupScreen(innerPadding = innerPadding, onBackToLogin = { navController.popBackStack() })
            }
        }

        composable(AppDestinations.Home.route) {
            LoadingState(message = "Redirecionando para o perfil do usuário...")
        }

    }
}