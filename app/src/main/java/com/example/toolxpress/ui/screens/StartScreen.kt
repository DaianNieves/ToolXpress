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
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toolxpress.R
import com.example.toolxpress.ui.components.CategoryHeader
import com.example.toolxpress.ui.theme.GrayProduct
import com.example.toolxpress.ui.components.TopBar
import com.example.toolxpress.ui.components.Product
import com.example.toolxpress.ui.components.ProductCard
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavController, allCategories: List<Pair<String, List<Product>>>) {
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
                        animationSpec = tween(
                            durationMillis = 4000,
                            easing = LinearEasing
                        ) // Animación más suave
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
                    .width(380.dp)  // Reducido el tamaño de la imagen
                    .padding(0.dp)
            ) {
                Image(
                    painter = painterResource(id = offerImages[page]), // Carga la imagen
                    contentDescription = "Oferta",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(20.dp)) // Bordes redondeados para la imagen
                        .padding(8.dp), // Ajustar el padding si es necesario
                    contentScale = ContentScale.Fit // Ajuste para que la imagen no se recorte
                )
            }
        }
    }
}

@Composable
fun StartScreen(
    navController: NavController,
    allCategories: List<Pair<String, List<Product>>>
) {
    // Nombres de las categorías
    val buttonTexts = allCategories.map { it.first }

    // Lista personalizada de imágenes o íconos para cada categoría
    val buttonIcons = listOf(
        R.drawable.fondo1,               // Imagen para la primera categoría
        R.drawable.fondo1,
        R.drawable.fondo1,
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
            .padding(0.dp, 20.dp, 0.dp, 20.dp)
    ) {
        Column {
            // Usando el CategoryHeader para el título
            CategoryHeader(categoryName = "Categorías")

            Spacer(modifier = Modifier.height(10.dp))

            // Contenedor de las categorías
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp), // Más espacio para que respire
                verticalArrangement = Arrangement.spacedBy(16.dp) // Espaciado entre recuadros
            ) {
                val numColumns = 2 // Reducido a 2 columnas para hacer los recuadros más grandes
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
                                // Recuadro de cada categoría
                                Box(
                                    modifier = Modifier
                                        .size(180.dp, 200.dp) // Aumentado para mejor visibilidad
                                        .clip(RoundedCornerShape(16.dp)) // Bordes más suaves
                                        .shadow(
                                            8.dp,
                                            RoundedCornerShape(16.dp)
                                        ) // Sombras elegantes
                                        .clickable {
                                            navController.navigate("ProductsScreen/${buttonTexts[index]}")
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    // Imagen de fondo
                                    Box(
                                        modifier = Modifier
                                            .matchParentSize() // Asegura que la imagen cubra todo el área del cuadro
                                            .clip(RoundedCornerShape(16.dp)) // Mantiene el mismo borde redondeado
                                    ) {
                                        when (val icon = buttonIcons[index]) {
                                            is Int -> Image(
                                                painter = painterResource(id = icon),
                                                contentDescription = "Imagen de ${buttonTexts[index]}",
                                                modifier = Modifier
                                                    .fillMaxSize() // Asegura que la imagen cubra todo el cuadro
                                                    .clip(RoundedCornerShape(16.dp)), // Mantiene el borde redondeado
                                                contentScale = ContentScale.Crop // Ajusta la escala de la imagen
                                            )

                                            is ImageVector -> Icon(
                                                imageVector = icon,
                                                contentDescription = "Ícono de ${buttonTexts[index]}",
                                                modifier = Modifier.size(80.dp), // Tamaño más grande del ícono
                                                tint = Color.Black
                                            )
                                        }
                                    }

                                    // Contenido del cuadro
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.SpaceEvenly,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(8.dp) // Espaciado interno para el texto
                                    ) {
                                        Text(
                                            text = buttonTexts[index],
                                            fontSize = 18.sp, // Aumentar tamaño de la fuente para mayor claridad
                                            fontWeight = FontWeight.Medium, // Texto más elegante
                                            color = Color.DarkGray,
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

@Composable
fun ProductScreen(navController: NavController) {
    Column {
        // Usando el CategoryHeader para el título
        CategoryHeader(categoryName = "Destacados")

        Spacer(modifier = Modifier.height(24.dp))
    }

    val products = listOf(
        Product(
            7,
            "Taladro Inalámbrico",
            "NANWEI Kit de Taladro Inalámbrico Electrico",
            594.00, R.drawable.taladro
        ),
        Product(
            8,
            "Pulidora inalámbrica",
            "Esmeriladora Angular Pulidora Inalambrica Con Accesorios",
            799.00, R.drawable.pulidora
        ),
        Product(
            2,
            "Kit desarmador",
            "Juego P/reparación De Celulares Y Disp. Electrónicos,77 Pzas",
            295.00, R.drawable.desarmador
        ),
        Product(
            10,
            "Pistola de calor",
            "RexQualis de 2000w Temperatura Regulable 4 Boquillas",
            384.0, R.drawable.pistolacalor
        ),
        Product(
            1,
            "Engrapadora",
            "Engrapadora Tipo Pistola Para Tapiceria Con 3000 Grapas",
            188.00, R.drawable.engrapadora
        ),
        Product(
            5,
            "Pinza de presión",
            "Pinza Presión 10' Mordaza Recta Pretul Granel Pretul 2270",
            94.00, R.drawable.pinza
        ),
        Product(
            6,
            "Escalera Tubular",
            "Escalera Tubular, Plegable, 2 Peldaños, Pretul Pretul 24118",
            595.00, R.drawable.escaleras
        ),
        Product(
            4,
            "Martillo Uña Recta",
            "Martillo Uña Recta, 16oz, Mango Fibra De Vidrio Truper 19997",
            149.00, R.drawable.martillo
        )
    )
    ProductGrid(products = products, navController = navController)
    }