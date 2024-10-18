package com.example.toolxpress.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toolxpress.R
import com.example.toolxpress.ui.theme.GrayProduct
import com.example.toolxpress.ui.components.TopBar
import com.example.toolxpress.data.model.PostModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavController, allCategories: List<Pair<String, List<PostModel>>>) {
    Column {
        // La barra superior fija
        Box {
            TopBar(navController)
        }

        // Contenedor con scroll para StartScreen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())  // Activar el scroll solo en este bloque
        ) {
            // Contenido desplazable
            OfferCarousel()
            StartScreen(navController, allCategories)
            ProductScreen(navController)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OfferCarousel() {
    // Lista de recursos de imágenes para las ofertas
    val offerImages = listOf(
        R.drawable.promo3,
        R.drawable.promo2,
        R.drawable.promo1,
        R.drawable.promo4,
        R.drawable.promo5
    )

    // Estado del Pager
    val pagerState = rememberPagerState(initialPage = 0) // Crear el PagerState una sola vez

    // Estado para controlar si se debe iniciar el desplazamiento automático
    var autoScroll by remember { mutableStateOf(true) }

    // Usar un CoroutineScope para manejar el desplazamiento automático
    val coroutineScope = rememberCoroutineScope()

    // Inicia un LaunchedEffect para el desplazamiento automático
    LaunchedEffect(autoScroll) {
        if (autoScroll) {
            while (true) {
                delay(5000) // Espera 5 segundos
                coroutineScope.launch {
                    // Avanza al siguiente índice con una animación más suave
                    pagerState.animateScrollToPage(
                        page = (pagerState.currentPage + 1) % offerImages.size,
                        animationSpec = tween(durationMillis = 4000, easing = LinearEasing) // Animación más suave
                    )
                }
            }
        }
    }

    // Contenedor principal para el carrusel de ofertas
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {
                        // Cuando el usuario comienza a arrastrar, detener el desplazamiento automático
                        autoScroll = false
                    },
                    onDragEnd = {
                        // Reiniciar el desplazamiento automático al soltar
                        autoScroll = true
                    },
                    onDrag = { change, dragAmount ->
                        // Consume el cambio de toque
                        change.consume()
                    }
                )
            },
        contentAlignment = Alignment.Center // Centra el contenido del Box
    ) {
        // Carrusel horizontal de imágenes de ofertas
        HorizontalPager(
            count = offerImages.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            itemSpacing = 8.dp
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(420.dp)
                    .background(Color.White)
                    .padding(0.dp)
            ) {
                Image(
                    painter = painterResource(id = offerImages[page]), // Carga la imagen
                    contentDescription = "Oferta",
                    modifier = Modifier
                        .fillMaxSize()
                        .height(200.dp)
                        .clip(RoundedCornerShape(30.dp)) // Bordes redondeados para la imagen
                )
            }
        }
    }
}

@Composable
fun StartScreen(
    navController: NavController,
    allCategories: List<Pair<String, List<PostModel>>>
) {
    // Nombres de las categorías
    val buttonTexts = allCategories.map { it.first }

    // Lista personalizada de imágenes o íconos para cada categoría
    val buttonIcons = listOf(
        R.drawable.logo,               // Imagen para la primera categoría
        R.drawable.logo,
        R.drawable.logo,
        Icons.Default.Computer,         // Ícono para la segunda categoría
        Icons.Default.ShoppingCart,     // Ícono para la tercera categoría
        Icons.Default.Home,             // Ícono adicional
        Icons.Default.FitnessCenter     // Ícono adicional
    )

    require(buttonIcons.size >= buttonTexts.size) {
        "El número de íconos debe ser igual o mayor al número de categorías"
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(0.dp, 20.dp, 0.dp, 20.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp, 20.dp, 14.dp, 0.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(GrayProduct),
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
                                        .size(120.dp, 180.dp) // Aumentar tamaño de los recuadros
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
                                        when (val icon = buttonIcons[index]) {
                                            is Int -> Image(
                                                painter = painterResource(id = icon),
                                                contentDescription = "Imagen de ${buttonTexts[index]}",
                                                modifier = Modifier.size(80.dp) // Aumentar tamaño del ícono
                                            )
                                            is ImageVector -> Icon(
                                                imageVector = icon,
                                                contentDescription = "Ícono de ${buttonTexts[index]}",
                                                modifier = Modifier.size(60.dp), // Aumentar tamaño del ícono
                                                tint = Color.Black
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = buttonTexts[index],
                                            fontSize = 16.sp, // Aumentar tamaño de la fuente
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
    val image: Any // Puede ser un ImageVector o un Int (R.drawable)
)


@Composable
fun ProductScreen(navController: NavController) {
    val products = listOf(
        Product("Producto 1", "Descripción del producto 1", 100.00, R.drawable.logo),
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
            ) {
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
                        horizontalArrangement = Arrangement.spacedBy(30.dp)
                    ) {
                        repeat(numColumns) { columnIndex ->
                            val index = rowIndex * numColumns + columnIndex
                            if (index < products.size) {
                                val product = products[index]
                                Box(
                                    modifier = Modifier
                                        .size(150.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color.LightGray)
                                        .clickable { navController.navigate("CardProducts") },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        // Verificar si el campo 'image' es un drawable o un ícono
                                        when (val image = product.image) {
                                            is Int -> Image(
                                                painter = painterResource(id = image),
                                                contentDescription = product.name,
                                                modifier = Modifier.size(60.dp)
                                            )
                                            is ImageVector -> Icon(
                                                imageVector = image,
                                                contentDescription = product.name,
                                                modifier = Modifier.size(40.dp),
                                                tint = Color.Black
                                            )
                                        }

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
