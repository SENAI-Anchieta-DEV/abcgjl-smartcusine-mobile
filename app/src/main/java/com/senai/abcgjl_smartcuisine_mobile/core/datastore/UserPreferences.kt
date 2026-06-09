package com.senai.abcgjl_smartcuisine_mobile.core.datastore

import android.content.Context
import android.content.SharedPreferences

import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserPreferences @Inject constructor(
    @ApplicationContext context: Context
) {

    private val prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_TOKEN = "jwt_token"
    }

    fun saveToken(token: String) {
        prefs.edit().putString(KEY_TOKEN, token).apply()
    }

    fun getToken(): String {
        return prefs.getString(KEY_TOKEN, "") ?: ""
    }

    fun clearToken() {
        prefs.edit().remove(KEY_TOKEN).apply()
    }

    fun salvarLogin(email: String, senha: String) {
        prefs.edit()
            .putString("email", email)
            .putString("senha", senha)
            .putBoolean("lembrar", true)
            .apply()
    }

    fun limparLogin() {
        prefs.edit()
            .remove("email")
            .remove("senha")
            .remove("lembrar")
            .apply()
    }

    fun getEmail(): String? = prefs.getString("email", null)
    fun getSenha(): String? = prefs.getString("senha", null)
    fun isLembrar(): Boolean = prefs.getBoolean("lembrar", false)
}