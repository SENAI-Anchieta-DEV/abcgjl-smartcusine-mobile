package com.senai.abcgjl_smartcuisine_mobile.app.shell

data class DrawerMenuItem(
    val title: String,
    val action: () -> Unit
)