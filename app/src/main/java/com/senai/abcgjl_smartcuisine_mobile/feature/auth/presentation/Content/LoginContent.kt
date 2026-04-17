import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.SmartCuisineTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senai.abcgjl_smartcuisine_mobile.R
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.ui.components.background.AuthGradientBackground

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    login: String = "",
    senha: String = "",
    onLoginChange: (String) -> Unit = {},
    onSenhaChange: (String) -> Unit = {},
    onLoginClick: () -> Unit = {},
    onCadastroClick: () -> Unit = {},
    onEsqueciSenhaClick: () -> Unit = {},
    isLoading: Boolean = false,
    lembrar: Boolean = false,
    onLembrarChange: (Boolean) -> Unit = {},


) {
    Box(
    ) {

        AuthGradientBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            Image(
                painter = painterResource(id = R.drawable.logosmartcuisine),
                contentDescription = "logo smartcuisine",
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Surface(
                shape = RoundedCornerShape(24.dp),
                color = Color.White,
                tonalElevation = 8.dp,
                shadowElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Login",
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = login,
                        onValueChange = onLoginChange,
                        placeholder = { Text("Seu email") },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Email,
                                contentDescription = "Email",
                                tint = Color.Gray
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    var passwordVisible by remember { mutableStateOf(false) }

                    TextField(
                        value = senha,
                        onValueChange = { onSenhaChange(it) },
                        placeholder = { Text("Senha") },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),

                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Lock,
                                contentDescription = "Senha",
                                tint = Color.Gray
                            )
                        },

                        trailingIcon = {
                            val image = if (passwordVisible)
                                Icons.Filled.Visibility
                            else
                                Icons.Filled.VisibilityOff

                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(imageVector = image, contentDescription = null)
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = lembrar,
                                onCheckedChange = onLembrarChange
                            )
                            Text("Lembrar senha", fontSize = 12.sp)
                        }
                        Text(
                            text = "Esqueceu a senha?",
                            fontSize = 12.sp,
                            color = Color(0xFF4A90E2),
                            modifier = Modifier.clickable {
                                onEsqueciSenhaClick()
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = onLoginClick,
                        enabled = !isLoading,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE6863B)
                        )
                    ) {if (isLoading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(24.dp),
                            strokeWidth = 2.dp
                        )} else{
                        Text("Entrar", color = Color.White)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Ou", fontSize = 12.sp)

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = onCadastroClick,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.LightGray
                        )
                    ) {
                        Text("Cadastre-se")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginContentClaro() {
    SmartCuisineTheme(darkTheme = false) {
        LoginContent()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginContentEscuro() {
    SmartCuisineTheme(darkTheme = true) {
        LoginContent()
    }
}