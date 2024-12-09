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
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toolxpress.R
import com.example.toolxpress.data.model.Product
import com.example.toolxpress.ui.components.CategoryHeader
import com.example.toolxpress.ui.theme.GrayProduct
import com.example.toolxpress.ui.components.TopBar
import com.example.toolxpress.ui.components.ProductCard
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavController, allCategories: List<Pair<String, List<Product>>>) {
    Column {
        Box {
            TopBar(navController)
        }
        Spacer(modifier = Modifier.height(14.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            OfferCarousel()
            StartScreen(navController, allCategories)
            ProductScreen(navController)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OfferCarousel() {
    val offerImages = listOf(
        R.drawable.promo3,
        R.drawable.promo2,
        R.drawable.promo1,
        R.drawable.promo4,
        R.drawable.promo5
    )

    val pagerState = rememberPagerState(initialPage = 0)
    var autoScroll by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(autoScroll) {
        if (autoScroll) {
            while (true) {
                delay(5000) // Espera 5 segundos
                coroutineScope.launch {
                    pagerState.animateScrollToPage(
                        page = (pagerState.currentPage + 1) % offerImages.size,
                        animationSpec = tween(
                            durationMillis = 4000,
                            easing = LinearEasing
                        )
                    )
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f) // Relación uniforme para el contenedor del carrusel
    ) {
        HorizontalPager(
            count = offerImages.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black) // Fondo opcional para los bordes vacíos
            ) {
                Image(
                    painter = painterResource(id = offerImages[page]),
                    contentDescription = "Oferta",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f), // Ajusta todas las imágenes a la misma proporción
                    contentScale = ContentScale.Fit // Asegura que las imágenes se vean completas
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
    val buttonTexts = allCategories.map { it.first }

    val buttonIcons = listOf(
        R.drawable.fondo1,
        R.drawable.fondo1,
        R.drawable.fondo1,
        Icons.Default.Computer,
        Icons.Default.ShoppingCart,
        Icons.Default.Home,
        Icons.Default.FitnessCenter
    )

    require(buttonIcons.size >= buttonTexts.size) {
        "El número de íconos debe ser igual o mayor al número de categorías"
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(0.dp, 20.dp, 0.dp, 20.dp)


    ) {

        CategoryHeader(categoryName = "Categorías")

        Spacer(modifier = Modifier.height(10.dp))


        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(buttonTexts.size) { index ->
                // Caja de cada categoría
                Box(
                    modifier = Modifier
                        .size(150.dp) // Tamaño estándar para cada tarjeta
                        .clip(RoundedCornerShape(16.dp))
                        .clickable {
                            navController.navigate("ProductsScreen/${buttonTexts[index]}")
                        },
                    contentAlignment = Alignment.Center
                ) {
                    // Imagen de fondo
                    when (val icon = buttonIcons[index]) {
                        is Int -> Image(
                            painter = painterResource(id = icon),
                            contentDescription = "Imagen de ${buttonTexts[index]}",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    // Texto sobre la imagen
                    Text(
                        text = buttonTexts[index],
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
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