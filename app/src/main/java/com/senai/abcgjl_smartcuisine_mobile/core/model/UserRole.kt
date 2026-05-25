package com.senai.abcgjl_smartcuisine_mobile.core.model

enum class UserRole {
    ADMINISTRADOR,
    GERENTE,
    COZINHEIRO;

    companion object {
        fun fromApiValue(value: String?): UserRole? {
            val normalized = value
                ?.trim()
                ?.replace('-', '_')
                ?.replace(' ', '_')
                ?.uppercase()
                ?: return null

            return entries.firstOrNull { it.name == normalized }
        }
    }
}