package com.senai.abcgjl_smartcuisine_mobile.feature.signup.data.repository

import android.content.Context
import android.net.Uri
import com.senai.abcgjl_smartcuisine_mobile.core.common.network.ApiExceptionParser
import com.senai.abcgjl_smartcuisine_mobile.core.model.UserRole
import com.senai.abcgjl_smartcuisine_mobile.feature.signup.data.remote.SignupApiService
import com.senai.abcgjl_smartcuisine_mobile.feature.signup.data.remote.request.SignupRequestDto
import com.senai.abcgjl_smartcuisine_mobile.feature.signup.domain.model.SignupForm
import com.senai.abcgjl_smartcuisine_mobile.feature.signup.domain.model.SignupResult
import com.senai.abcgjl_smartcuisine_mobile.feature.signup.domain.repository.SignupRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class SignupRepositoryImpl @Inject constructor(
    private val signupApiService: SignupApiService,
    @ApplicationContext private val context: Context
) : SignupRepository {

    override suspend fun register(form: SignupForm): SignupResult {
        return try {
            val response = signupApiService.register(
                SignupRequestDto(
                    nome = form.nome.trim(),
                    email = form.email.trim(),
                    senha = form.senha,
                    tipoUsuario = form.role.name
                )
            )
            SignupResult(
                enrollment = response.enrollment ?: "",
                status = response.status,
                message = response.message,
                token = response.token
            )
        } catch (throwable: Throwable) {
            throw IllegalStateException(
                ApiExceptionParser.toMessage(
                    throwable = throwable,
                    defaultMessage = "Não foi possível enviar o cadastro para a API."
                )
            )
        }
    }

    private fun createPhotoPart(photoUri: String?): MultipartBody.Part? {
        if (photoUri.isNullOrBlank()) return null

        val uri = Uri.parse(photoUri)
        val inputStream = context.contentResolver.openInputStream(uri) ?: return null
        val tempFile = File.createTempFile("signup_photo_", ".jpg", context.cacheDir)

        inputStream.use { input ->
            tempFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }

        val mimeType = context.contentResolver.getType(uri) ?: "image/*"
        val requestFile = tempFile.asRequestBody(mimeType.toMediaType())

        return MultipartBody.Part.createFormData(
            name = "foto",
            filename = tempFile.name,
            body = requestFile
        )
    }
}

private fun String.toTextRequestBody(): RequestBody =
    toRequestBody("multipart/form-data".toMediaType())