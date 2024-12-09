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

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(navController = navController)
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                item {

                    // Imagen centrada (Logo)
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
                    // Email/Usuario
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
                                    email = it.filter { char -> char != ' ' }
                                    hasEmailError = !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()
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
                                singleLine = true,  // Asegura que el input se mantenga en una sola línea
                                maxLines = 1        // Limita el input a una línea
                            )
                        }
                        if (hasEmailError) {
                            Text(
                                text = "Por favor, introduzca una dirección de correo electrónico válida.",
                                color = YellowIcons,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }

                item {
                    // Campo de texto para contraseña - mostrar/ocultar contraseña
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
                                contentDescription = "User Icon",
                                modifier = Modifier.size(28.dp),
                                tint = BlueBackground
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            BasicTextField(
                                value = password,
                                onValueChange = {
                                    password = it.filter { char -> char != ' ' }//filtrar espacios
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
                                singleLine = true,  // input se mantenga en una sola línea
                                maxLines = 1
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
                                text = "Please enter your password.",
                                color = YellowIcons,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }

                item {
                    // Remember me
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Checkbox(
                            checked = keepLoggedIn,
                            onCheckedChange = { keepLoggedIn = it },
                            colors = CheckboxDefaults.colors(
                                checkmarkColor = BlueBackground, // Color de la marca cuando está seleccionado
                                uncheckedColor = Color.White, // Borde blanco cuando está desmarcado
                                checkedColor = YellowIcons // Borde y relleno amarillo cuando está seleccionado
                            )
                        )
                        Text(
                            text = "Recuérdame",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(60.dp))
                }

                item {
                    // Botón de inicio de sesión
                    Button(
                        onClick = { navController.navigate("StartScreen") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 25.dp)
                            .height(50.dp), // Altura ajustada a 50.dp
                        colors = ButtonDefaults.buttonColors(
                            containerColor = YellowIcons, // Color de fondo amarillo
                            contentColor = Color.White // Color del contenido, en este caso el texto
                        ),
                        shape = RoundedCornerShape(16.dp) // Borde redondeado para mejor diseño
                    ) {
                        Text(
                            text = "Iniciar sesión",
                            fontSize = 30.sp, // Ajuste del tamaño de fuente a 15.sp
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = BlueBackground // Color del texto blanco
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
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
