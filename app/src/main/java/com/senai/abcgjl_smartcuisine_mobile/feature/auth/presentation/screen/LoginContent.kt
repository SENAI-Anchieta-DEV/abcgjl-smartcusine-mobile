import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.SmartCuisineTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lint.kotlin.metadata.Visibility
import com.senai.abcgjl_smartcuisine_mobile.R
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    login: String = "",
    senha: String = "",
    onLoginChange: (String) -> Unit = {},
    onSenhaChange: (String) -> Unit = {},
    onLoginClick: () -> Unit = {}
) {
    Box(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFBFA2FF).copy(alpha = 0.4f),
                            Color.Transparent
                        ),
                        center = Offset(200f, 1400f),
                        radius = 1000f
                    )
                )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFAEDCFF).copy(alpha = 0.4f),
                            Color.Transparent
                        ),
                        center = Offset(800f, 200f),
                        radius = 1000f
                    )
                )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFEB863A).copy(alpha = 0.35f),
                            Color.Transparent
                        ),
                        center = Offset(1000f, 800f),
                        radius = 1000f
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
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

                    // 📩 Email
                    TextField(
                        value = login,
                        onValueChange = onLoginChange,
                        placeholder = { Text("Seu email") },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier.fillMaxWidth()
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
                        trailingIcon = {
                            val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
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
                        Text("Me lembre", fontSize = 12.sp)
                        Text("Esqueceu a senha?", fontSize = 12.sp, color = Color(0xFF4A90E2))
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = onLoginClick,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE6863B)
                        )
                    ) {
                        Text("Entrar", color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Ou", fontSize = 12.sp)

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {},
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