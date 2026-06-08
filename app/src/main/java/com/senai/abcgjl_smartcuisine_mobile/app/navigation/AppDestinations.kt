package com.senai.abcgjl_smartcuisine_mobile.app.navigation

sealed class AppDestinations(val route: String) {
    data object Login : AppDestinations("login")
    data object Signup : AppDestinations("signup")
    data object Home : AppDestinations("home")
    data object AdmHome : AppDestinations("adm_home")
}