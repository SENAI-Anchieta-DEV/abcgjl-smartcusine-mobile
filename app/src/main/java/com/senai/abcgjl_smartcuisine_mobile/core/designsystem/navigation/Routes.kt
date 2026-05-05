package com.senai.abcgjl_smartcuisine_mobile.core.designsystem.navigation

sealed class Routes(val route: String) {
    data object Login : Routes("login")
    data object Home : Routes("home")
    data object Cadastro : Routes("cadastro")
    data object EsqueciSenha : Routes("esqueci_senha")

    object HomeAdmin : Routes("home_admin")

}