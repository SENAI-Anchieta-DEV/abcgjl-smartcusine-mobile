package com.senai.abcgjl_smartcuisine_mobile.core.network.datastore

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {

    private val prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_TOKEN = "jwt_token"
    }

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(KEY_TOKEN, token).apply()
    }

    fun getToken(): String {
        return sharedPreferences.getString(KEY_TOKEN, "") ?: ""
    }

    fun clearToken() {
        sharedPreferences.edit().remove(KEY_TOKEN).apply()
    }

    fun salvarLogin(email: String, senha: String) {
        prefs.edit()
            .putString("email", email)
            .putString("senha", senha)
            .putBoolean("lembrar", true)
            .apply()
    }

    fun limparLogin() {
        prefs.edit().clear().apply()
    }

    fun getEmail(): String? = prefs.getString("email", null)
    fun getSenha(): String? = prefs.getString("senha", null)
    fun isLembrar(): Boolean = prefs.getBoolean("lembrar", false)
}
