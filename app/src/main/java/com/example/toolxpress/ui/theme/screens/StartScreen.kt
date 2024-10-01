package com.example.toolxpress.ui.theme.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.toolxpress.R


@Composable
fun MainScreen(navController: NavController) {
    Column {
        // La barra superior fija
        TopBar()

        // Espacio entre el carrusel y el contenido desplazable
        Spacer(modifier = Modifier.height(16.dp))

        // Contenedor con scroll para StartScreen y ProductScreen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())  // Activar el scroll solo en este bloque
        ) {
            // Contenido desplazable
            OfferCarousel()
            StartScreen()
            ProductScreen()
            Footer()
        }
    }
}

@Composable
fun TopBar() {
    var searchText = "" // campo de búsqueda

    // Contenedor principal de la barra superior
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp)
            .statusBarsPadding() // espacio de la barra de estado
            .background(Color(0xFFE66410))

    ) {
        // Fila que contiene el menú, búsqueda, ícono de persona y el ícono del carrito
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(vertical = 8.dp)
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically, // Alineación vertical centrada
            horizontalArrangement = Arrangement.SpaceBetween // Espaciado entre elementos
        ) {
            // Ícono de menú desplegable
            IconButton(
                onClick = { /* Acción del menú desplegable */ },
                modifier = Modifier.padding(end = 5.dp) // Espacio a la derecha del ícono
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu, // Cambia esto si deseas otro ícono
                    contentDescription = "Menú",
                    tint = Color.White // Color del ícono
                )
            }

            // Campo de búsqueda
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it }, // Asegúrate de actualizar el texto de búsqueda
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(end = 5.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Buscar",
                        tint = Color(0xFFE66410) // Usando el color e66410
                    )
                },
                singleLine = true, // Solo una línea
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Blue,
                    unfocusedIndicatorColor = Color.Gray
                )
            )
            // Ícono de persona
            IconButton(
                onClick = { /* Acción de perfil */ },
                modifier = Modifier.padding(start = 5.dp) // Espacio a la izquierda del ícono
            ) {
                Icon(
                    imageVector = Icons.Filled.Person, // Reemplaza esto con el ícono que desees
                    contentDescription = "Perfil",
                    tint = Color.White // Color del ícono
                )
            }
            // Ícono del carrito
            Box(modifier = Modifier.wrapContentSize()) {
                IconButton(
                    onClick = { /* Acción del carrito */ },
                    modifier = Modifier.background(Color(0xFFE66410)) // Fondo azul para el botón
                ) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "Carrito de Compras",
                        tint = Color.White
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
    }
}


@Composable
fun OfferCarousel() {
    // Lista de recursos de imágenes para las ofertas
    val offerImages = listOf(
        R.drawable.logo,
        R.drawable.logo,
        R.drawable.logo,
        R.drawable.logo,
        R.drawable.logo
    )

    // Contenedor principal para el carrusel de ofertas
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(0.dp),
        contentAlignment = Alignment.Center // Centra el contenido del Box
    ) {
        // Carrusel horizontal de imágenes de ofertas
        LazyRow(
            modifier = Modifier
                .height(200.dp)
        ) {
            items(offerImages) { imageResId ->
                Box(
                    modifier = Modifier
                        .padding(0.dp)
                        .fillMaxHeight()
                        .width(420.dp)
                        .height(170.dp)
                        .background(Color.White)

                ) {
                    Image(
                        painter = painterResource(id = imageResId), // Carga la imagen
                        contentDescription = "Oferta",
                        modifier = Modifier
                            .fillMaxSize()
                            .height(200.dp)
                            .clip(RoundedCornerShape(16.dp)) // Bordes redondeados para la imagen
                    )
                }
            }
        }
    }
}
@Composable
fun StartScreen() {

    val buttonTexts = listOf(
        "Herramientas 1",
        "Herramientas 2",
        "Herramientas 3",
        "Herramientas 4",
        "Herramientas 5",
        "Herramientas 6"
    )
    val buttonIcons = listOf(
        Icons.Default.Build,
        Icons.Default.Build,
        Icons.Default.Build,
        Icons.Default.Build,
        Icons.Default.Build,
        Icons.Default.Build
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(0.dp, 5.dp, 0.dp, 20.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp, 50.dp, 14.dp, 0.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xff2C2C2C)),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Categorias",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val numColumns = 3 // Número de columnas por fila
                val numRows = (buttonTexts.size + numColumns - 1) / numColumns

                repeat(numRows) { rowIndex ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        repeat(numColumns) { columnIndex ->
                            val index = rowIndex * numColumns + columnIndex
                            if (index < buttonTexts.size) {
                                Box(
                                    modifier = Modifier
                                        .size(100.dp, 150.dp) // Tamaño ajustado como los productos
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color.LightGray)
                                        .clickable { /* Acción del botón */ },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.SpaceEvenly
                                    ) {
                                        Icon(
                                            imageVector = buttonIcons[index],
                                            contentDescription = "Ícono de la herramienta",
                                            modifier = Modifier.size(40.dp),
                                            tint = Color.Black
                                        )
                                        Text(
                                            text = buttonTexts[index],
                                            fontSize = 14.sp,
                                            color = Color.Black,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


data class Product(
    val name: String,
    val description: String,
    val price: Double,
    val icon: ImageVector // Campo para almacenar el ícono
)

@Composable
fun ProductScreen() {
    val products = listOf(
        Product("Producto 1", "Descripción del producto 1", 100.00, Icons.Default.Build),
        Product("Producto 2", "Descripción del producto 2", 150.00, Icons.Default.ShoppingCart),
        Product("Producto 3", "Descripción del producto 3", 200.00, Icons.Default.FavoriteBorder),
        Product("Producto 4", "Descripción del producto 4", 250.00, Icons.Default.Star),
        Product("Producto 5", "Descripción del producto 5", 300.00, Icons.Default.Person),
        Product("Producto 6", "Descripción del producto 6", 350.00, Icons.Default.Home),
        Product("Producto 7", "Descripción del producto 7", 400.00, Icons.Default.Settings),
        Product("Producto 8", "Descripción del producto 8", 450.00, Icons.Default.Info)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(0.dp, 5.dp, 0.dp, 20.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp, 0.dp, 14.dp, 0.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xff2C2C2C)),
                horizontalArrangement = Arrangement.Center
            )  {
                Text(
                    text = "Destacados",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val numColumns = 2 // Número de columnas por fila
                val numRows = (products.size + numColumns - 1) / numColumns

                repeat(numRows) { rowIndex ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally),
                        horizontalArrangement = Arrangement.spacedBy(30.dp) // Espaciado más amplio
                    ) {
                        repeat(numColumns) { columnIndex ->
                            val index = rowIndex * numColumns + columnIndex
                            if (index < products.size) {
                                val product = products[index]
                                Box(
                                    modifier = Modifier
                                        .size(150.dp) // Tamaño aumentado como las categorías originales
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color.LightGray)
                                        .clickable { /* Acción del botón */ },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        // Usamos el ícono específico de cada producto
                                        Icon(
                                            imageVector = product.icon,
                                            contentDescription = "Ícono del producto",
                                            modifier = Modifier.size(40.dp),
                                            tint = Color.Black
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = product.name,
                                            fontSize = 14.sp,
                                            color = Color.Black,
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            text = product.description,
                                            fontSize = 12.sp,
                                            color = Color.Gray,
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            text = "$${product.price}",
                                            fontSize = 14.sp,
                                            color = Color.Black
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
//  menú superior
@Composable
fun TopMenu() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Menu,
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
            imageVector = Icons.Default.Home,
            contentDescription = "Home",
            tint = Color.White,
            modifier = Modifier.clickable {
                // Acción para ir a la pantalla principal
            }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "Cart",
            tint = Color.White,
            modifier = Modifier.clickable {
                // Acción para ir al carrito de compras
            }
        )
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
