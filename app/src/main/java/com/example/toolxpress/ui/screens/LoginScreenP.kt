package com.example.toolxpress.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toolxpress.R
import com.example.toolxpress.ui.components.TopBar
import com.example.toolxpress.ui.theme.BlueBackground
import com.example.toolxpress.ui.theme.YellowIcons

@Composable
fun LoginScreenP(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var keepLoggedIn by remember { mutableStateOf(false) }
    var hasEmailError by remember { mutableStateOf(false) }
    var hasPasswordError by remember { mutableStateOf(false) }
    var loginErrorMessage by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(navController = navController)
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                item {
                    // Logo e imagen
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Logo",
                            modifier = Modifier.size(200.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }

                item {
                    // Campo de correo/usuario
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Correo o Nombre de usuario",
                            color = YellowIcons,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 8.dp, bottom = 6.dp)
                        )
                        Row(
                            modifier = Modifier
                                .background(Color.White.copy(alpha = 0.7f), shape = MaterialTheme.shapes.medium)
                                .padding(14.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.PermIdentity,
                                contentDescription = "User Icon",
                                modifier = Modifier.size(28.dp),
                                tint = BlueBackground
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            BasicTextField(
                                value = email,
                                onValueChange = {
                                    email = it.trim() // Filtrar espacios al inicio y fin
                                    hasEmailError = email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                                },
                                modifier = Modifier.weight(1f),
                                decorationBox = { innerTextField ->
                                    Box(Modifier.fillMaxWidth()) {
                                        if (email.isEmpty()) {
                                            Text(
                                                "Ingrese su correo electrónico o nombre de usuario",
                                                color = BlueBackground,
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        innerTextField()
                                    }
                                },
                                textStyle = LocalTextStyle.current.copy(
                                    color = Color.Black,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                singleLine = true
                            )
                        }
                        if (hasEmailError) {
                            Text(
                                text = "Por favor, introduzca un correo válido.",
                                color = YellowIcons,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }

                item {
                    // Campo de contraseña
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Contraseña",
                            color = YellowIcons,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 8.dp, bottom = 6.dp)
                        )
                        Row(
                            modifier = Modifier
                                .background(Color.White.copy(alpha = 0.7f), shape = MaterialTheme.shapes.medium)
                                .padding(14.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Password,
                                contentDescription = "Password Icon",
                                modifier = Modifier.size(28.dp),
                                tint = BlueBackground
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            BasicTextField(
                                value = password,
                                onValueChange = {
                                    password = it.trim() // Filtrar espacios
                                    hasPasswordError = password.isEmpty()
                                },
                                modifier = Modifier.weight(1f),
                                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                                decorationBox = { innerTextField ->
                                    Box(Modifier.fillMaxWidth()) {
                                        if (password.isEmpty()) {
                                            Text(
                                                "Ingrese su contraseña",
                                                color = BlueBackground,
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        innerTextField()
                                    }
                                },
                                textStyle = LocalTextStyle.current.copy(
                                    color = Color.Black,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                singleLine = true
                            )
                            Icon(
                                imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = "Toggle Password Visibility",
                                modifier = Modifier
                                    .size(28.dp)
                                    .clickable { passwordVisible = !passwordVisible },
                                tint = BlueBackground
                            )
                        }
                        if (hasPasswordError) {
                            Text(
                                text = "Por favor, ingrese su contraseña.",
                                color = YellowIcons,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }

                item {
                    if (loginErrorMessage != null) {
                        Text(
                            text = loginErrorMessage!!,
                            color = YellowIcons,
                            modifier = Modifier.padding(8.dp),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Button(
                        onClick = {
                            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                                hasEmailError = true
                                loginErrorMessage = "Por favor, verifique su correo."
                            } else if (password.isEmpty()) {
                                hasPasswordError = true
                                loginErrorMessage = "Por favor, introduzca su contraseña."
                            } else {
                                loginErrorMessage = null
                                navController.navigate("StartScreen")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 25.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = YellowIcons,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Iniciar sesión",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = BlueBackground
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        ClickableText(
                            text = AnnotatedString("Crear una nueva cuenta"),
                            onClick = {
                                navController.navigate("createAccount")
                            },
                            style = LocalTextStyle.current.copy(
                                color = Color.White,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                textDecoration = TextDecoration.Underline
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        )
    }
}