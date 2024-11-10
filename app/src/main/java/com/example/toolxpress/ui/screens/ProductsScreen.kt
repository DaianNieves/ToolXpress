package com.example.toolxpress.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.example.toolxpress.ui.components.CategoryHeader
import com.example.toolxpress.ui.components.ProductCard
import com.example.toolxpress.ui.components.TopBar
import com.example.toolxpress.data.model.PostModel

@Composable
fun ProductsScreen(
    navController: NavController,
    categoryName: String?,
    allCategories: List<Pair<String, List<PostModel>>>
) {
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
                allCategories.forEach { (categoryName, posts) ->
                    item { CategoryHeader(categoryName) }

                    item {
                        ProductGrid(posts, navController)
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

@Composable
fun ProductGrid(posts: List<PostModel>, navController: NavController) {
    BoxWithConstraints {
        val columns =
            if (maxWidth < 600.dp) 2 else 4  // Cambiar el número de columnas según el ancho

        val totalItems = posts.size
        val rows = (totalItems + columns - 1) / columns

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(rows) { rowIndex ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    for (colIndex in 0 until columns) {
                        val itemIndex = rowIndex * columns + colIndex
                        if (itemIndex < totalItems) {
                            val post = posts[itemIndex]
                            ProductCard(post = post, navController = navController)
                        } else {
                            Spacer(modifier = Modifier.weight(1f))  // Espacio vacío para rellenar columnas
                        }
                    }
                }
            }
        }
    }
}