package com.example.toolxpress.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Handyman
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toolxpress.R
import com.example.toolxpress.ui.theme.GrayProduct
import com.example.toolxpress.ui.theme.Orange
import com.example.toolxpress.ui.theme.BlueBackground
import com.example.toolxpress.ui.theme.YellowIcons

@Composable
fun TopBar(navController: NavController) {
    var searchText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) } // Estado para controlar la expansión del menú

    // Contenedor principal de la barra superior
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding() // Espacio de la barra de estado
            .background(YellowIcons)
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
                    tint = BlueBackground, // Color del ícono
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
                        tint = BlueBackground
                    )
                },
                singleLine = true, // Mantener una sola línea
                shape = RoundedCornerShape(25.dp),
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
                    tint = BlueBackground,// Color del ícono
                    modifier = Modifier.size(30.dp)
                )
            }

            // Ícono del carrito
            Box(modifier = Modifier.wrapContentSize()) {
                IconButton(
                    onClick = { navController.navigate("ShoppingCart") },
                ) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "Carrito de Compras",
                        tint = BlueBackground,
                        modifier = Modifier.size(30.dp)
                    )
                }
                // Círculo con el número de notificaciones
                Box(
                    modifier = Modifier
                        .size(20.dp) // Tamaño del círculo
                        .background(Color.Transparent, shape = CircleShape)// Color naranja
                        .align(Alignment.TopEnd) // Alineación en la esquina superior derecha
                ) {
                    Text(
                        text = "", // Número de notificaciones
                        color = BlueBackground,
                        fontSize = 16.sp, // Tamaño de fuente
                        modifier = Modifier.align(Alignment.Center) // Centrado en el círculo
                    )
                }
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(Color.White)  // Fondo blanco del menú
        ) {
            ProductDataProvider { categories ->

                // Opción "Mi perfil"
                DropdownMenuItemWithBackground(
                    onClick = {
                        expanded = false
                        navController.navigate("DataUserScreen")
                    },
                    backgroundColor = Color(0xFFFFF3E0)  // Naranja claro
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Perfil",
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Mi perfil",
                            color = Color.Black,
                            fontSize = 24.sp
                        )
                    }
                }

                // Divider después de la opción "Mi perfil"
                Divider(color = Color.Gray, thickness = .5.dp)

                // Opción "Inicio"
                DropdownMenuItemWithBackground(
                    onClick = {
                        expanded = false
                        navController.navigate("StartScreen")
                    },
                    backgroundColor = Color(0xFFE3F2FD)  // Azul claro
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Handyman,
                            contentDescription = "Inicio",
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Inicio",
                            color = Color.Black,
                            fontSize = 16.sp
                        )
                    }
                }

                // Divider después de la opción "Inicio"
                Divider(color = Color.Gray, thickness = .5.dp)

                // Opción "Mis compras"
                DropdownMenuItemWithBackground(
                    onClick = {
                        expanded = false
                        navController.navigate("ComprasScreen")
                    },
                    backgroundColor = Color(0xFFE3F2FD)  // Azul claro
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingBag,
                            contentDescription = "Compras",
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Mis compras",
                            color = Color.Black,
                            fontSize = 16.sp
                        )
                    }
                }

                // Divider después de la opción "Mis compras"
                Divider(color = Color.Gray, thickness = .5.dp)

                // Opción "Todos los Productos"
                DropdownMenuItemWithBackground(
                    onClick = {
                        expanded = false
                        navController.navigate("ProductsScreen")
                    },
                    backgroundColor = Color.White
                ) {
                    Text(
                        text = "Todos los productos",
                        color = Color.Black,
                        fontSize = 16.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                // Divider después de la opción "Todos los Productos"
                Divider(color = Color.Gray, thickness = .5.dp)

                // Opciones para cada categoría
                categories.forEach { (categoryName, _) ->
                    DropdownMenuItemWithBackground(
                        onClick = {
                            expanded = false
                            navController.navigate("ProductsScreen/$categoryName")
                        },
                        backgroundColor = Color(0xFFF1F8E9)  // Verde claro
                    ) {
                        Text(
                            text = categoryName,
                            color = Color.Black,
                            fontSize = 16.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DropdownMenuItemWithBackground(
    onClick: () -> Unit,
    backgroundColor: Color,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()  // Ocupa todo el ancho del contenedor
            .background(backgroundColor)  // Fondo sin interrupciones
            .clickable(onClick = onClick)  // Habilita clics
            .padding(vertical = 16.dp, horizontal = 20.dp)  // Espaciado vertical adecuado
    ) {
        content()
    }
}

//IMPLEMENTAR A FUTURO BIEN
@Composable
fun ReturnBar(navController: NavController, title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Orange)
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Regresar",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.width(3.dp))

            Text(
                text = title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val image: Int)

@Composable
fun ProductCard(
    product: Product,
    navController: NavController,
    width: Dp = 170.dp,
    height: Dp = 250.dp
) {
    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .clickable { navController.navigate("CardProducts") },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painterResource(id = product.image),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
            Text(
                text = product.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            Text(
                text = product.description,
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis // Añade puntos suspensivos si el texto es muy largo
            )
            Text(
                text = "$${product.price}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}


@Composable
fun ProductDataProvider(
    content: @Composable (categories: List<Pair<String, List<Product>>>) -> Unit
) {
    val tools = listOf(
        Product(1, "Engrapadora", "Engrapadora Tipo Pistola Para Tapiceria Con 3000 Grapas", 188.0, R.drawable.engrapadora),
        Product(2, "Kit desarmador", "Juego P/reparación De Celulares Y Disp. Electrónicos,77 Pzas", 295.0, R.drawable.desarmador),
        Product(3, "Pinza de presión", "Pinza Presión 10' Mordaza Recta Pretul Granel Pretul 2270", 94.0, R.drawable.pinza),
        Product(4, "Martillo Uña Recta", "Martillo Uña Recta, 16oz, Mango Fibra De Vidrio Truper 19997", 149.00, R.drawable.martillo),
        Product(5, "Pinza de presión", "Pinza Presión 10' Mordaza Recta Pretul Granel Pretul 2270", 94.00, R.drawable.pinza),
        Product(6, "Escalera Tubular", "Escalera Tubular, Plegable, 2 Peldaños, Pretul Pretul 24118", 595.00, R.drawable.escaleras)
    )

    val powerTools = listOf(
        Product(7, "Taladro Inalámbrico", "NANWEI Kit de Taladro Inalámbrico Electrico", 594.0, R.drawable.taladro),
        Product(8, "Pulidora inalámbrica", "Esmeriladora Angular Pulidora Inalambrica Con Accesorios", 799.0, R.drawable.pulidora),
        Product(9, "Lijadora", "Lijadora Roto Orbital Profesional Shawty C/16 Lija 14000 Opm", 748.0, R.drawable.lijadora),
        Product(10, "Pistola de calor", "RexQualis de 2000w Temperatura Regulable 4 Boquillas", 384.0, R.drawable.pistolacalor)
    )

    val measurementTools = listOf(
        Product(11, "Multímetro Digital", "Multímetro Digital Profesional Xl830l Medidor Corriente Mano", 93.0, R.drawable.multimetro),
        Product(12, "Calibrador digital", "Calibrador Digital RexQualis 6in Precisión 0.01mm Metal", 249.0, R.drawable.calibrador),
        Product(13, "Multímetro de gancho", "AstroAI Multimetro de Gancho, Pinza Amperimétrica", 610.0, R.drawable.calibradorgancho)
    )

    val categories = listOf(
        "Herramientas manuales" to tools,
        "Herramientas eléctricas" to powerTools,
        "Herramientas de medición" to measurementTools
    )

    content(categories)
}

@Composable
fun CategoryHeader(categoryName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(GrayProduct)
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = categoryName,
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ProductCardCompact(id: Int, title: String, text: String, image: Painter) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black,
            contentColor = Color.White
        )
    ) {
        Row() {
            Image(
                modifier = Modifier
                    .width(80.dp)
                    .height(100.dp)
                    .padding(5.dp),
                painter = image,
                contentDescription = "Android Logo",
                contentScale = ContentScale.FillBounds
            )
            Column {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )
                Text(
                    text = text,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Justify,
                    maxLines = 3,
                    modifier = Modifier.padding(10.dp)

                )

            }
        }
    }
}