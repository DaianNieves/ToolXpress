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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toolxpress.ui.theme.components.CategoryHeader
import com.example.toolxpress.ui.theme.components.Footer
import com.example.toolxpress.ui.theme.components.ProductCard
import com.example.toolxpress.ui.theme.components.ProductDataProvider
import com.example.toolxpress.ui.theme.components.TopBar
import com.example.toolxpress.ui.theme.data.model.PostModel

@Composable
fun ProductsScreen(navController: NavController, categoryName: String?, allCategories: List<Pair<String, List<PostModel>>>) {
    // Filtrar los productos según la categoría seleccionada
    val selectedProducts = if (categoryName != null) {
        allCategories.find { it.first == categoryName }?.second ?: emptyList()
    } else {
        // Si no hay categoría seleccionada, muestra todos los productos
        allCategories.flatMap { it.second }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Barra superior fija (TopBar)
        TopBar(navController = navController)

        // Contenido desplazable dentro del LazyColumn
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f) // Ocupar el resto del espacio disponible
        ) {
            // Si se han seleccionado productos, mostrar las categorías y sus productos
            if (categoryName == null) {
                // Muestra todas las categorías y productos
                allCategories.forEach { (categoryName, posts) ->
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
            } else {
                // Si hay categoría seleccionada, mostrar solo los productos de esa categoría
                item {
                    // Muestra el encabezado de la categoría seleccionada
                    CategoryHeader(categoryName)
                }

                if (selectedProducts.isEmpty()) {
                    item {
                        Text(
                            text = "No hay productos disponibles para esta categoría.",
                            modifier = Modifier.padding(16.dp),
                            fontSize = 18.sp,
                            color = Color.Gray
                        )
                    }
                } else {
                    // Agregamos los productos de la categoría seleccionada
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp) // Espacio entre productos
                        ) {
                            // Dividir productos en filas de 2
                            val totalItems = selectedProducts.size
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
                                        val leftPost = selectedProducts[leftIndex]
                                        ProductCard(post = leftPost)
                                    }

                                    // Producto para la columna derecha
                                    val rightIndex = leftIndex + 1
                                    if (rightIndex < totalItems) {
                                        val rightPost = selectedProducts[rightIndex]
                                        ProductCard(post = rightPost)
                                    }
                                }
                            }
                        }
                    }
                }

                // Espacio vacío para empujar el footer hacia abajo
                item {
                    Spacer(modifier = Modifier.height(200.dp).weight(1f))
                }
            }

            // Barra inferior fija (Footer) - Mantenido siempre al final
            item {
                Footer()
            }
        }
    }
}