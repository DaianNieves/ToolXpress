package com.example.toolxpress.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
                    .width(420.dp)
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
            // Título de la sección
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

data class Product(
    val name: String,
    val description: String,
    val price: Double,
    val image: Any // Puede ser un ImageVector o un Int (R.drawable)
)


@Composable
fun ProductScreen(navController: NavController) {
    val products = listOf(
        Product(
            "Taladro " +
                    "Inalámbrico",
            "NANWEI Kit de Taladro Inalámbrico Electrico",
            594.00, R.drawable.taladro
        ),
        Product(
            "Pulidora inalámbrica",
            "Esmeriladora Angular Pulidora Inalambrica Con Accesorios",
            799.00, R.drawable.pulidora
        ),
        Product(
            "Kit desarmador", "Juego P/reparación De Celulares Y Disp. Electrónicos,77 Pzas",
            295.00, R.drawable.desarmador
        ),
        Product(
            "Pistola de calor",
            "RexQualis de 2000w Temperatura Regulable 4 Boquillas",
            384.0, R.drawable.pistolacalor
        ),
        Product(
            "Engrapadora",
            "Engrapadora Tipo Pistola Para Tapiceria Con 3000 Grapas",
            188.00, R.drawable.engrapadora
        ),
        Product(
            "Pinza de presión",
            "Pinza Presión 10' Mordaza Recta Pretul Granel Pretul 2270",
            94.00, R.drawable.pinza
        ),
        Product(
            "Escalera Tubular",
            "Escalera Tubular, Plegable, 2 Peldaños, Pretul Pretul 24118",
            595.00, R.drawable.escaleras
        ),
        Product(
            "Martillo Uña Recta",
            "Martillo Uña Recta, 16oz, Mango Fibra De Vidrio Truper 19997",
            149.00, R.drawable.martillo
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 20.dp, 0.dp, 20.dp)
    ) {
        Column {
            // Título "Destacados"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
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

            // Tarjetas de productos
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp) // Espaciado entre las filas
            ) {
                val numColumns = 2
                val numRows = (products.size + numColumns - 1) / numColumns

                repeat(numRows) { rowIndex ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp) // Espaciado entre columnas
                    ) {
                        repeat(numColumns) { columnIndex ->
                            val index = rowIndex * numColumns + columnIndex
                            if (index < products.size) {
                                val product = products[index]
                                Box(
                                    modifier = Modifier
                                        .weight(1f) // Para que las columnas tengan el mismo peso
                                        .aspectRatio(0.7f) // Ajusta esta relación para mantener proporciones
                                ) {
                                    ProductCard(product = product, navController = navController)
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
fun ProductCard(product: Product, navController: NavController) {
    Box(
        modifier = Modifier
            .width(600.dp) // Aumentar el ancho de la tarjeta
            .height(800.dp) // Aumentar la altura de la tarjeta
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable { navController.navigate("CardProducts") },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp), // Espacio más grande entre los elementos
            modifier = Modifier.padding(12.dp) // Añadimos padding dentro de la tarjeta
        ) {
            // Imagen del producto o icono
            when (val image = product.image) {
                is Int -> Image(
                    painter = painterResource(id = image),
                    contentDescription = product.name,
                    modifier = Modifier.size(100.dp) // Aumentar el tamaño de la imagen
                )

                is ImageVector -> Icon(
                    imageVector = image,
                    contentDescription = product.name,
                    modifier = Modifier.size(60.dp), // Ícono más grande
                    tint = Color.Black
                )
            }

            // Nombre del producto
            Text(
                text = product.name,
                fontSize = 18.sp, // Aumentar tamaño del texto
                fontWeight = FontWeight.Bold, // Texto del nombre en negrita
                color = Color.Black,
                textAlign = TextAlign.Center,
                maxLines = 1, // Limitar a una línea
                overflow = TextOverflow.Ellipsis, // Puntos suspensivos si es muy largo
                fontFamily = FontFamily.SansSerif // Fuente similar a Amazon
            )

            // Descripción del producto
            Text(
                text = product.description,
                fontSize = 16.sp, // Aumentar tamaño de texto de descripción
                color = Color.Gray,
                textAlign = TextAlign.Justify, // Justificar el texto
                maxLines = Int.MAX_VALUE, // Mostrar todas las líneas
                overflow = TextOverflow.Visible, // Mostrar todo el texto
                fontFamily = FontFamily.SansSerif // Fuente similar a Amazon
            )

            // Precio del producto
            Text(
                text = "$${product.price}",
                fontSize = 20.sp, // Aumentar tamaño del precio
                fontWeight = FontWeight.Bold, // Precio en negrita
                color = Color.Black,
                fontFamily = FontFamily.SansSerif // Fuente similar a Amazon
            )
        }
    }
}


