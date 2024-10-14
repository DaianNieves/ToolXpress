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
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            val totalItems = posts.size
                            val rows = (totalItems + 1) / 2

                            repeat(rows) { rowIndex ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 20.dp, vertical = 10.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    val leftIndex = rowIndex * 2
                                    if (leftIndex < totalItems) {
                                        val leftPost = posts[leftIndex]
                                        ProductCard(
                                            post = leftPost,
                                            navController = navController
                                        )
                                    }

                                    val rightIndex = leftIndex + 1
                                    if (rightIndex < totalItems) {
                                        val rightPost = posts[rightIndex]
                                        ProductCard(
                                            post = rightPost,
                                            navController = navController
                                        )
                                    }
                                }
                            }
                        }
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
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            val totalItems = selectedProducts.size
                            val rows = (totalItems + 1) / 2

                            repeat(rows) { rowIndex ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 20.dp, vertical = 10.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    val leftIndex = rowIndex * 2
                                    if (leftIndex < totalItems) {
                                        val leftPost = selectedProducts[leftIndex]
                                        ProductCard(
                                            post = leftPost,
                                            navController = navController
                                        )
                                    }

                                    val rightIndex = leftIndex + 1
                                    if (rightIndex < totalItems) {
                                        val rightPost = selectedProducts[rightIndex]
                                        ProductCard(
                                            post = rightPost,
                                            navController = navController
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(200.dp).weight(1f)) }
            item { Footer() }
        }
    }
}
