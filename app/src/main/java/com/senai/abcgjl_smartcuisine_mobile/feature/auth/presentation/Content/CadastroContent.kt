package com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.Content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.senai.abcgjl_smartcuisine_mobile.R
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.SmartCuisineTheme

@Composable
fun CadastroContent(
    modifier: Modifier = Modifier,
    nome: String = "",
    email: String = "",
    senha: String = "",
    confirmarSenha: String = "",
    perfil: String = "",
    onNomeChange: (String) -> Unit = {},
    onEmailChange: (String) -> Unit = {},
    onSenhaChange: (String) -> Unit = {},
    onConfirmarSenhaChange: (String) -> Unit = {},
    onPerfilChange: (String) -> Unit = {},
    isLoading: Boolean = false,
    onCadastrarClick: () -> Unit = {}
) {

    val perfis = listOf("Administrador", "Cozinheiro", "Gerente")

    Box(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFBFA2FF).copy(alpha = 0.6f),
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
                            Color(0xFFAEDCFF).copy(alpha = 0.6f),
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
                            Color(0xFFEB863A).copy(alpha = 0.6f),
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
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            Image(
                painter = painterResource(
                    id = R.drawable.logosmartcuisine
                ),
                contentDescription = "logo",
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

                    Text("Cadastro", style = MaterialTheme.typography.headlineMedium)

                    Spacer(modifier = Modifier.height(16.dp))

                    var expanded by remember { mutableStateOf(false) }

                    Box {
                        TextField(
                            value = perfil,
                            onValueChange = {},
                            readOnly = true,
                            placeholder = { Text("Selecione o perfil") },
                            shape = RoundedCornerShape(50),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp), // altura mais fina
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Person,
                                    contentDescription = "Perfil",
                                    tint = Color.Gray
                                )
                            },
                            trailingIcon = {
                                IconButton(onClick = { expanded = true }) {
                                    Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                                }
                            },
                            singleLine = true
                        )

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            perfis.forEach {
                                DropdownMenuItem(
                                    text = { Text(it) },
                                    onClick = {
                                        onPerfilChange(it)
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    TextField(
                        value = nome,
                        onValueChange = onNomeChange,
                        placeholder = { Text("Nome completo") },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = "Nome",
                                tint = Color.Gray
                            )
                        },
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    TextField(
                        value = email,
                        onValueChange = onEmailChange,
                        placeholder = { Text("Email") },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Email,
                                contentDescription = "Email",
                                tint = Color.Gray
                            )
                        },
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    var senhaVisivel by remember { mutableStateOf(false) }

                    TextField(
                        value = senha,
                        onValueChange = onSenhaChange,
                        placeholder = { Text("Senha") },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        visualTransformation = if (senhaVisivel) VisualTransformation.None else PasswordVisualTransformation(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Lock,
                                contentDescription = "Senha",
                                tint = Color.Gray
                            )
                        },
                        trailingIcon = {
                            val icon = if (senhaVisivel) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                            IconButton(onClick = { senhaVisivel = !senhaVisivel }) {
                                Icon(icon, contentDescription = null)
                            }
                        },
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    var confirmarVisivel by remember { mutableStateOf(false) }

                    TextField(
                        value = confirmarSenha,
                        onValueChange = onConfirmarSenhaChange,
                        placeholder = { Text("Confirmar senha") },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        visualTransformation = if (confirmarVisivel) VisualTransformation.None else PasswordVisualTransformation(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Lock,
                                contentDescription = "Confirmar senha",
                                tint = Color.Gray
                            )
                        },
                        trailingIcon = {
                            val icon = if (confirmarVisivel) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                            IconButton(onClick = { confirmarVisivel = !confirmarVisivel }) {
                                Icon(icon, contentDescription = null)
                            }
                        },
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = onCadastrarClick,
                        enabled = !isLoading,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE6863B),
                            disabledContainerColor = Color(0xFFE67E22)
                        )
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier.size(24.dp),
                                strokeWidth = 2.dp
                            )}
                            else
                            {Text("Cadastrar", color = Color.White)}
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
        CadastroContent()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginContentEscuro() {
    SmartCuisineTheme(darkTheme = true) {
        CadastroContent()
    }
}