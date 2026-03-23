package com.senai.abcgjl_smartcuisine_mobile.core.designsystem.navigation

sealed class Routes(val route: String) {
    data object Login : Routes("login")
    data object Home : Routes("home")

    data object SmartCuisine : Routes("smartcuisine")
}