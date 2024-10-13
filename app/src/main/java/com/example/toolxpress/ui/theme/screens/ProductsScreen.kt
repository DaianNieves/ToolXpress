package com.example.toolxpress.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.toolxpress.R
import com.example.toolxpress.ui.theme.components.CategoryHeader
import com.example.toolxpress.ui.theme.components.Footer
import com.example.toolxpress.ui.theme.components.ProductCard
import com.example.toolxpress.ui.theme.components.TopBar
import com.example.toolxpress.ui.theme.data.model.PostModel


@Composable
fun ProductsScreen(navController: NavController) {
    // Datos de ejemplo para las categorías
    val postsCategory1 = listOf(
        PostModel(1, "Title 1", "Price$1", painterResource(R.drawable.ejemploimagen)),
        PostModel(2, "Title 2", "Price$2", painterResource(R.drawable.ejemploimagen))
    )

    val postsCategory2 = listOf(
        PostModel(3, "Title 3", "Price$3", painterResource(R.drawable.ejemploimagen)),
        PostModel(4, "Title 4", "Price$4", painterResource(R.drawable.ejemploimagen)),
        PostModel(5, "Title 5", "Price$5", painterResource(R.drawable.ejemploimagen))
    )

    val postsCategory3 = listOf(
        PostModel(6, "Title 6", "Price$ 6", painterResource(R.drawable.ejemploimagen)),
        PostModel(7, "Title 7", "Price$ 7", painterResource(R.drawable.ejemploimagen)),
        PostModel(8, "Title 8", "Price$ 8", painterResource(R.drawable.ejemploimagen)),
        PostModel(9, "Title 9", "Price$ 9", painterResource(R.drawable.ejemploimagen))
    )

    val categories = listOf(
        "Categoría 1" to postsCategory1,
        "Categoría 2" to postsCategory2,
        "Categoría 3" to postsCategory3
    )

    Column(modifier = Modifier.fillMaxSize()) {
        // Barra superior fija (TopBar)
        TopBar(navController = navController)

        // Contenido desplazable dentro del LazyColumn
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f) // Ocupar el resto del espacio disponible
        ) {
            // Contenido de categorías y productos
            categories.forEach { (categoryName, posts) ->
                // Agregamos la categoría como un encabezado de ancho completo
                item {
                    CategoryHeader(categoryName)
                }

                // Agregamos los productos para esta categoría
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp) // Espacio entre categorías
                    ) {
                        // Dividir productos en filas de 2
                        val totalItems = posts.size
                        val rows = (totalItems + 1) / 2 // Calculamos el número de filas

                        repeat(rows) { rowIndex ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp, vertical = 10.dp), // Padding horizontal y vertical
                                horizontalArrangement = Arrangement.SpaceBetween // Espaciado uniforme
                            ) {
                                // Producto para la columna izquierda
                                val leftIndex = rowIndex * 2
                                if (leftIndex < totalItems) {
                                    val leftPost = posts[leftIndex]
                                    ProductCard(post = leftPost)
                                }

                                // Producto para la columna derecha
                                val rightIndex = leftIndex + 1
                                if (rightIndex < totalItems) {
                                    val rightPost = posts[rightIndex]
                                    ProductCard(post = rightPost)
                                }
                            }
                        }
                    }
                }
            }

            // Espacio antes del footer
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Barra inferior fija (Footer)
            item {
                Footer()
            }
        }
    }
}