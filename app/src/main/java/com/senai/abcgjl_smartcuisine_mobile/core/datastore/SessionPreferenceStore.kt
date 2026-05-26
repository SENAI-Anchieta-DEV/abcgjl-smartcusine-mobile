package com.senai.abcgjl_smartcuisine_mobile.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.senai.abcgjl_smartcuisine_mobile.core.model.ApprovalStatus
import com.senai.abcgjl_smartcuisine_mobile.core.model.SessionUser
import com.senai.abcgjl_smartcuisine_mobile.core.model.UserRole
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

private val Context.sessionDataStore by preferencesDataStore(name = "session_preferences")

class SessionPreferenceStore(
    private val context: Context
) {
    private object Keys {
        val authToken = stringPreferencesKey("auth_token")
        val userId = stringPreferencesKey("user_id")
        val userName = stringPreferencesKey("user_name")
        val userEmail = stringPreferencesKey("user_email")
        val userRole = stringPreferencesKey("user_role")
        val photoUrl = stringPreferencesKey("photo_url")
        val enrollment = stringPreferencesKey("enrollment")
        val approvalStatus = stringPreferencesKey("approval_status")
    }

    val sessionUser: Flow<SessionUser?> = context.sessionDataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val token = preferences[Keys.authToken].orEmpty()
            if (token.isBlank()) {
                null
            } else {
                SessionUser(
                    nome = preferences[Keys.userName].orEmpty(),
                    email = preferences[Keys.userEmail].orEmpty(),
                    role = UserRole.fromApiValue(preferences[Keys.userRole]),
                    authToken = token,
                    approvalStatus = ApprovalStatus.fromApiValue(preferences[Keys.approvalStatus])
                )
            }
        }

    suspend fun saveSessionUser(user: SessionUser) {
        context.sessionDataStore.edit { preferences ->
            preferences[Keys.authToken] = user.authToken
            preferences[Keys.userName] = user.nome
            preferences[Keys.userEmail] = user.email
            user.role?.let { preferences[Keys.userRole] = it.name }
            user.approvalStatus?.let { preferences[Keys.approvalStatus] = it.name }
        }
    }

    suspend fun clear() {
        context.sessionDataStore.edit { it.clear() }
    }
}