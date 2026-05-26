package com.senai.abcgjl_smartcuisine_mobile.core.common.network

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import retrofit2.HttpException

object ApiExceptionParser {
    private val gson = Gson()

    fun toMessage(throwable: Throwable, defaultMessage: String): String {
        return when (throwable) {
            is HttpException -> parseHttpException(throwable) ?: defaultMessage
            else -> throwable.message ?: defaultMessage
        }
    }

    private fun parseHttpException(exception: HttpException): String? {
        val rawBody = exception.response()?.errorBody()?.string().orEmpty()
        if (rawBody.isBlank()) return null

        return runCatching {
            gson.fromJson(rawBody, ApiErrorBody::class.java)
        }.getOrNull()?.extractMessage()
    }
}

data class ApiErrorBody(
    @SerializedName("message", alternate = ["mensagem", "detail", "erro"])
    val message: String? = null,
    @SerializedName("errors", alternate = ["erros"])
    val errors: List<String>? = null
) {
    fun extractMessage(): String? = when {
        !message.isNullOrBlank() -> message
        !errors.isNullOrEmpty() -> errors.firstOrNull()
        else -> null
    }
}