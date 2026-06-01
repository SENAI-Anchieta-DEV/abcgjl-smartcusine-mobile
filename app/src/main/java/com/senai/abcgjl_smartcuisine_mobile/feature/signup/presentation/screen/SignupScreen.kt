import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.senai.abcgjl_smartcuisine_mobile.feature.signup.presentation.component.SignupForm
import com.senai.abcgjl_smartcuisine_mobile.feature.signup.presentation.viewmodel.SignupViewModel

@Composable
fun SignupScreen(
    innerPadding: PaddingValues,
    onBackToLogin: () -> Unit,
    viewModel: SignupViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SignupForm(
        uiState = uiState,
        onNameChange = { viewModel.onNameChange(it) },
        onEmailChange = { viewModel.onEmailChange(it) },
        onPasswordChange = { viewModel.onPasswordChange(it) },
        onRoleSelected = { viewModel.onRoleSelected(it) },
        onSubmit = { viewModel.submit() },
        onBackToLogin = onBackToLogin,
        modifier = Modifier.padding(innerPadding)
    )
}