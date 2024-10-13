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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.toolxpress.R
import com.example.toolxpress.ui.theme.GreyProduct
import com.example.toolxpress.ui.theme.components.Footer
import com.example.toolxpress.ui.theme.components.ProductDataProvider
import com.example.toolxpress.ui.theme.components.TopBar
import com.example.toolxpress.ui.theme.data.model.PostModel

@Composable
fun MainScreen(navController: NavController, allCategories: List<Pair<String, List<PostModel>>>) {
    Column {
        // La barra superior fija
        Box {
            TopBar(navController)
        }
        // Espacio entre el carrusel y el contenido desplazable
        Spacer(modifier = Modifier.height(16.dp))

        // Contenedor con scroll para StartScreen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())  // Activar el scroll solo en este bloque
        ) {
            // Contenido desplazable
            OfferCarousel()
            StartScreen(navController, allCategories)
            ProductScreen()
            // La barra inferior fija
            Box {
                Footer()
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
fun StartScreen(navController: NavController, allCategories: List<Pair<String, List<PostModel>>>) {
    val buttonTexts = allCategories.map { it.first }
    val buttonIcons = List(buttonTexts.size) { Icons.Default.Build } // Ajusta los íconos según tus necesidades

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
                    .background(GreyProduct),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Categorías",
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
                                        .size(100.dp, 150.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color.LightGray)
                                        .clickable {
                                            navController.navigate("ProductsScreen/${buttonTexts[index]}")
                                        },
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
