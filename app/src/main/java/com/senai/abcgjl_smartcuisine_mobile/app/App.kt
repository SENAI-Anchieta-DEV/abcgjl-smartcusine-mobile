package com.senai.abcgjl_smartcuisine_mobile.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.SmartCuisineTheme

@Composable
fun App(){
    SmartCuisineTheme() {
        val navController =  rememberNavController()
        AppNavHost(
            navController = navController,
        )
    }
}