package com.senai.abcgjl_smartcuisine_mobile.feature.signup.presentation.screen

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.layout.AppScreen
import com.senai.abcgjl_smartcuisine_mobile.feature.signup.domain.model.SignupForm
import com.senai.abcgjl_smartcuisine_mobile.feature.signup.presentation.component.SignupHeader
import com.senai.abcgjl_smartcuisine_mobile.feature.signup.presentation.viewmodel.SignupViewModel
import java.io.File

@Composable
fun SignupScreen(
    innerPadding: PaddingValues = PaddingValues(),
    onBackToLogin: () -> Unit,
    viewModel: SignupViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = androidx.compose.ui.platform.LocalContext.current
    var cameraImageUri by remember { mutableStateOf<Uri?>(null) }


    AppScreen(innerPadding = innerPadding) {
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            SignupHeader()
            //SignupForm(
                //uiState = uiState,
                //onNameChange = viewModel::onNameChange,
                //onEmailChange = viewModel::onEmailChange,
                //onSenhaChange = viewModel::onSenhaChange,
                //onRoleSelected = viewModel::onRoleSelected,
                //onCourseSelected = { courseId ->
                    //uiState.courses.firstOrNull { it.id == courseId }
                        //?.let(viewModel::onCourseSelected)
                //},
                //onSubmit = viewModel::submit,
                //onBackToLogin = onBackToLogin,
            //)
        }
    }
}

private fun createTempImageUri(context: Context): Uri {
    val imageDir = File(context.cacheDir, "images").apply { mkdirs() }
    val file = File.createTempFile("signup_", ".jpg", imageDir)
    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider",
        file
    )
}