package com.example.toolxpress.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toolxpress.R

@Composable
fun TopBar(navController: NavController) {
    var searchText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) } // Estado para controlar la expansión del menú

    // Contenedor principal de la barra superior
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding() // Espacio de la barra de estado
            .background(Color(0xFFE66410))
    ) {
        // Fila que contiene el menú, búsqueda, ícono de persona y el ícono del carrito
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp) // Ajustar la altura total para dar más espacio al campo de búsqueda
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically, // Alineación vertical centrada
            horizontalArrangement = Arrangement.SpaceBetween // Espaciado entre elementos
        ) {
            // Ícono de menú
            IconButton(
                onClick = { expanded = !expanded }, // Abre el menú desplegable
                modifier = Modifier.padding(end = 5.dp) // Espacio a la derecha del ícono
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menú",
                    tint = Color.White, // Color del ícono
                    modifier = Modifier.size(30.dp)
                )
            }

            // Campo de búsqueda
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it }, // Actualizar el texto de búsqueda
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp) // Altura ajustada del campo de texto
                    .padding(vertical = 0.dp, horizontal = 5.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Buscar",
                        tint = Color(0xFFE66410) // Usando el color e66410
                    )
                },
                singleLine = true, // Mantener una sola línea
                shape = RoundedCornerShape(18.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent, // Sin borde al enfocarse
                    unfocusedIndicatorColor = Color.Transparent // Sin borde cuando no está enfocado
                ),
                textStyle = androidx.compose.ui.text.TextStyle( // Aumentar el tamaño del texto si es necesario
                    fontSize = 16.sp
                )
            )

            // Ícono de persona
            IconButton(
                onClick = { navController.navigate("login") },
                modifier = Modifier.padding(start = 5.dp) // Espacio a la izquierda del ícono
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Perfil",
                    tint = Color.White,// Color del ícono
                    modifier = Modifier.size(30.dp)
                )
            }

            // Ícono del carrito
            Box(modifier = Modifier.wrapContentSize()) {
                IconButton(
                    onClick = { navController.navigate("ShoppingCart") },
                    modifier = Modifier.background(Color(0xFFE66410)) // Fondo del botón
                ) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "Carrito de Compras",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
                // Círculo con el número de notificaciones
                Box(
                    modifier = Modifier
                        .size(20.dp) // Tamaño del círculo
                        .background(Color(0xFFFFA500), shape = CircleShape) // Color naranja
                        .align(Alignment.TopEnd) // Alineación en la esquina superior derecha
                ) {
                    Text(
                        text = "7", // Número de notificaciones
                        color = Color.White,
                        fontSize = 12.sp, // Tamaño de fuente
                        modifier = Modifier.align(Alignment.Center) // Centrado en el círculo
                    )
                }
            }
        }

        // Menú desplegable para categorías
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(Color.White) // Fondo del menú desplegable
                .padding(8.dp) // Espaciado dentro del menú
        ) {
            // Lista de categorías
            val categories = listOf(
                "Categoria 1", "Categoria 2", "Categoria 3",
                "Categoria 4", "Categoria 5", "Categoria 6"
            )

            categories.forEach { category ->
                DropdownMenuItem(
                    onClick = {
                        // Aquí puedes añadir cualquier acción que quieras realizar al hacer clic
                        expanded = false // Cierra el menú
                    }
                ) {
                    Text(
                        text = category, // Texto de la categoría
                        color = Color.Black,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun Footer() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1E1E1E))
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Sección de Correos
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Correos",
                    color = Color.White,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "contacto@toolxpress.com",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }

            // Sección de Teléfonos
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Teléfonos",
                    color = Color.White,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "+52 123 456 7890",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
                Text(
                    text = "52 987 654 3210",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }

            // Descripción de la Empresa
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "TOOLXPRESS",
                    color = Color.White,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Herramientas especializadas.",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }

        // Espaciado entre las secciones y las redes sociales
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Redes sociales",
            color = Color.White,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Cambiar a imagenes
            SocialMediaImage(imageResId = R.drawable.facebook, contentDescription = "Facebook")
            SocialMediaImage(imageResId = R.drawable.x, contentDescription = "Twitter")
            SocialMediaImage(imageResId = R.drawable.instagram, contentDescription = "Instagram")
            SocialMediaImage(imageResId = R.drawable.whats, contentDescription = "whatsapp")
        }

        // Derechos reservados centrados horizontalmente
        Spacer(modifier = Modifier.height(16.dp))
        Divider(color = Color.Gray.copy(alpha = 0.5f), thickness = 1.dp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "© 2024 TOOLXPRESS - Todos los derechos reservados.",
            color = Color.Gray,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun SocialMediaImage(imageResId: Int, contentDescription: String) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(Color.White.copy(alpha = 0.1f), shape = CircleShape)
            .clickable { /* Acción para redes sociales */ },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview
@Composable
fun FooterPreview() {
    Footer()
}
