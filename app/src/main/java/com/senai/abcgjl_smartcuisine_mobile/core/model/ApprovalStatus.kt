package com.senai.abcgjl_smartcuisine_mobile.core.model

enum class ApprovalStatus {
    PENDENTE,
    APROVADO,
    REJEITADO;

    companion object {
        fun fromApiValue(value: String?): ApprovalStatus? {
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