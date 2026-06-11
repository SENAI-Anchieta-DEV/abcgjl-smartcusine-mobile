package com.senai.abcgjl_smartcuisine_mobile.app.navigation

import SignupScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.senai.abcgjl_smartcuisine_mobile.app.shell.NavigationIconType
import com.senai.abcgjl_smartcuisine_mobile.app.shell.UnauthenticatedShell
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.component.LoadingState
import com.senai.abcgjl_smartcuisine_mobile.core.session.SessionViewModel
import com.senai.abcgjl_smartcuisine_mobile.core.model.UserRole
import com.senai.abcgjl_smartcuisine_mobile.core.session.SessionState
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.screen.EsqueciSenhaScreen
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.screen.HomeAdminScreen
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.screen.LoginScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    sessionViewModel: SessionViewModel = hiltViewModel()
) {
    val sessionState = sessionViewModel.sessionState.collectAsStateWithLifecycle().value
    val sessionUser = sessionViewModel.sessionUser.collectAsStateWithLifecycle().value
    val navigateToLogin by sessionViewModel.navigateToLogin.collectAsStateWithLifecycle()


    fun authenticatedRouteForCurrentUser(): String {
        return when (sessionUser.role) {
            UserRole.ADMINISTRADOR -> AppDestinations.AdmHome.route
            UserRole.GERENTE -> AppDestinations.AdmHome.route
            UserRole.COZINHEIRO -> AppDestinations.AdmHome.route
            null                   -> AppDestinations.GuestHome.route
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

    LaunchedEffect(navigateToLogin) {
        if (navigateToLogin) {
            sessionViewModel.consumeNavigateToLogin()
            navController.navigate(AppDestinations.Login.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                }
                launchSingleTop = true
            }
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
                UnauthenticatedShell(
                    title = "",
                    showHeader = false
                ) { innerPadding ->
                    LoginScreen(
                        innerPadding = innerPadding,
                        onNavigateToSignup = {
                            navController.navigate(AppDestinations.Signup.route)
                        },
                        onNavigateToHome = {
                            navController.navigate(AppDestinations.AdmHome.route)
                        },
                        onNavigateToEsqueciSenha = {
                            navController.navigate(AppDestinations.EsqueciSenha.route)
                        },
                        onNavigateAsGuest = {
                            navController.navigate(AppDestinations.GuestHome.route)
                        }
                    )
                }
            }
        }

        composable(AppDestinations.GuestHome.route) {
            HomeAdminScreen(navController = navController, sessionViewModel = sessionViewModel)
        }

        composable(AppDestinations.Signup.route) {
            UnauthenticatedShell(
                title = "",
                showHeader = false,
                navigationIconType = NavigationIconType.BACK,
                onNavigationClick = { navController.popBackStack() }
            ) { innerPadding ->
                SignupScreen(
                    innerPadding = innerPadding,
                    onBackToLogin = { navController.popBackStack() }
                )
            }
        }

        composable(AppDestinations.AdmHome.route) {
            HomeAdminScreen(navController = navController, sessionViewModel = sessionViewModel)
        }

        composable(AppDestinations.EsqueciSenha.route) {

                EsqueciSenhaScreen(
                    navController = navController
                )
            }
        }
    }