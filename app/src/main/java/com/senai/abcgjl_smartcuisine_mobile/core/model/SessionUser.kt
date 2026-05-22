package com.senai.abcgjl_smartcuisine_mobile.core.model

data class SessionUser(
    val name: String = "",
    val email: String = "",
    val role: UserRole? = null,
    val authToken: String = "",
    val approvalStatus: ApprovalStatus? = null
)