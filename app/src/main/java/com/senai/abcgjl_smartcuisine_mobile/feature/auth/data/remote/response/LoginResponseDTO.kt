package com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.remote.response

import com.google.gson.annotations.SerializedName
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.model.User

data class LoginResponseDTO(
    @SerializedName("accessToken", alternate = ["token"])
    val token: String,
    val user: User
)
