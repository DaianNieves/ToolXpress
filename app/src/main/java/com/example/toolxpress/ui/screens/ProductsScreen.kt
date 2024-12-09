package com.example.toolxpress.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toolxpress.data.model.Product
import com.example.toolxpress.ui.components.CategoryHeader
import com.example.toolxpress.ui.components.ProductCard
import com.example.toolxpress.ui.components.ProductDataProvider
import com.example.toolxpress.ui.components.TopBar

@Composable
fun ProductsScreen(
    navController: NavController,
    categoryName: String? = null,
    allCategories: List<Pair<String, List<Product>>>
) {
    ProductDataProvider { allCategories ->
        val selectedProducts = if (categoryName != null) {
            allCategories.find { it.first == categoryName }?.second ?: emptyList()
        } else {
            allCategories.flatMap { it.second }
        }

        Column(modifier = Modifier.fillMaxSize()) {
            TopBar(navController = navController)

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                if (categoryName == null) {
                    allCategories.forEach { (categoryName, products) ->
                        item { CategoryHeader(categoryName) }
                        item {
                            ProductGrid(products, navController)
                        }
                    }
                } else {
                    item { CategoryHeader(categoryName) }
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
                        item {
                            ProductGrid(selectedProducts, navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProductGrid(products: List<Product>, navController: NavController) {
    BoxWithConstraints {
        // Detectar la orientación y definir el número de columnas
        val columns = if (maxWidth < 600.dp) 2 else 4

        val totalItems = products.size
        val rows = (totalItems + columns - 1) / columns // Número de filas

        // Para evitar el espaciado excesivo al estar en modo horizontal, ajustamos el espaciado
        val horizontalPadding = 28.dp
        val verticalPadding = 16.dp

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding, vertical = verticalPadding),
            verticalArrangement = Arrangement.spacedBy(verticalPadding) // Espaciado entre las filas
        ) {
            repeat(rows) { rowIndex ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(horizontalPadding) // Espaciado entre columnas
                ) {
                    repeat(columns) { colIndex ->
                        val index = rowIndex * columns + colIndex
                        if (index < totalItems) {
                            val product = products[index]
                            Box(
                                modifier = Modifier
                                    .weight(1f) // Para que las columnas tengan el mismo tamaño
                                    .fillMaxWidth() // Aseguramos que las tarjetas llenen todo el ancho disponible
                            ) {
                                // Actualizamos el evento de clic para navegar a la pantalla del producto con su ID
                                ProductCard(
                                    product = product,
                                    navController = navController
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}