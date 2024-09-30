package com.example.toolxpress.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.ShoppingCart

import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toolxpress.R

@Composable
fun LoginScreenP(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) } // Estado para controlar la visibilidad de la contraseña
    var keepLoggedIn by remember { mutableStateOf(false) }
    var hasEmailError by remember { mutableStateOf(false) }
    var hasPasswordError by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo
        /*Image(
            painter = painterResource(id = R.drawable.ferreteria),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .blur(10.dp),
            contentScale = ContentScale.Crop
        )

        // Capa oscura
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.1f))
        )
*/
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .fillMaxWidth()

            ) {
                Spacer(modifier = Modifier.height(30.dp))
                // Menú superior
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.DarkGray)
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Icon(
                        imageVector = Icons.Default.Menu, // Usa un ícono de menú
                        contentDescription = "Menu",
                        tint = Color.White,
                        modifier = Modifier.clickable {
                            // Acción al hacer clic en el menú
                        }
                    )
                    Text(
                        text = "Categorías",
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Default.Home, // Ícono de Home
                        contentDescription = "Home",
                        tint = Color.White,
                        modifier = Modifier.clickable {
                            // Acción para ir a la pantalla principal
                        }
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = Icons.Default.ShoppingCart, // Ícono de carrito
                        contentDescription = "Cart",
                        tint = Color.White,
                        modifier = Modifier.clickable {
                            // Acción para ir al carrito de compras
                        }
                    )
                }
                Spacer(modifier = Modifier.height(40.dp)) // Espacio entre el menú y el contenido

                // Imagen centrada (Logo)
                Image(
                    painter = painterResource(id = R.drawable.logo), // Reemplaza con tu recurso de imagen
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(150.dp) // A  justa el tamaño de la imagen según sea necesario
                        .align(Alignment.CenterHorizontally) // Centrar horizontalmente
                )

                // Crear una cuenta
                Row(
                    horizontalArrangement = Arrangement.Absolute.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                ) {
                    Text(
                        text = "Log in",
                        fontWeight = FontWeight.Bold,
                        fontSize = 50.sp,
                        color = Color.Black

                    )
                }
                Spacer(modifier = Modifier.height(30.dp))

                // Email/Usuario
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Email or Username",
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp, bottom = 6.dp)
                    )
                    Row(
                        modifier = Modifier
                            .background(
                                Color.DarkGray.copy(alpha = 0.7f),
                                shape = MaterialTheme.shapes.medium
                            )
                            .padding(14.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.PermIdentity,
                            contentDescription = "User Icon",
                            modifier = Modifier.size(28.dp),
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        BasicTextField(
                            value = email,
                            onValueChange = {
                                email = it
                                // Validar si el correo contiene '@' y tiene un formato adecuado
                                hasEmailError = !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                                    .matches() && email.isNotEmpty()
                            },
                            modifier = Modifier.weight(1f),
                            decorationBox = { innerTextField ->
                                Box(Modifier.fillMaxWidth()) {
                                    if (email.isEmpty()) {
                                        Text(
                                            "Enter your email or username",
                                            color = Color.Gray,
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    innerTextField()
                                }
                            },
                            textStyle = LocalTextStyle.current.copy(
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }// Mostrar mensaje de error si el correo no es válido
                    if (hasEmailError) {
                        Text(
                            text = "Please enter a valid email address.",
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Campo de texto para contraseña - mostrar/ocultar contraseña
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Password",
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp, bottom = 6.dp)
                    )
                    Row(
                        modifier = Modifier
                            .background(
                                Color.DarkGray.copy(alpha = 0.7f),
                                shape = MaterialTheme.shapes.medium
                            )
                            .padding(14.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Password,
                            contentDescription = "User Icon",
                            modifier = Modifier.size(28.dp),
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        BasicTextField(
                            value = password,
                            onValueChange = {
                                password = it
                                hasPasswordError = password.isEmpty()
                            },
                            modifier = Modifier.weight(1f),
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            decorationBox = { innerTextField ->
                                Box(Modifier.fillMaxWidth()) {
                                    if (password.isEmpty()) {
                                        Text(
                                            "Enter your password",
                                            color = Color.Gray,
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    innerTextField()
                                }
                            },
                            textStyle = LocalTextStyle.current.copy(
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Icon(
                            imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = "Toggle Password Visibility",
                            modifier = Modifier
                                .size(28.dp)
                                .clickable { passwordVisible = !passwordVisible },
                            tint = Color.White
                        )
                    }
                    if (hasPasswordError) {
                        Text(
                            text = "Please enter your password.",
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Remember me"
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = keepLoggedIn,
                        onCheckedChange = { keepLoggedIn = it }
                    )
                    Text(
                        text = "Remember me",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(60.dp))

                // inicio de sesión
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFe66410)),
                    enabled = !hasEmailError && !hasPasswordError && email.isNotEmpty() && password.isNotEmpty() // Deshabilitar si hay errores o campos vacíos
                ) {
                    Text(
                        text = "Login",
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center // Centrar el contenido horizontalmente
                ) {
                    ClickableText(
                        text = AnnotatedString("Create a new account"),
                        onClick = {
                            navController.navigate("createAccount") // Navegar a la pantalla de creación de cuenta
                        },
                        style = LocalTextStyle.current.copy(
                            color = Color.Black,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline,

                            )
                    )

                }
            }
        }
    }
}

