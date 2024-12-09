package com.example.toolxpress.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.toolxpress.data.model.ProductViewModel
import com.example.toolxpress.data.model.ShoppingCartViewModel
import com.example.toolxpress.data.model.ShoppingModel
import com.example.toolxpress.ui.components.TopBar
import com.example.toolxpress.ui.theme.BlueBackground
import com.example.toolxpress.ui.theme.YellowIcons

@Composable
fun CardProducts(
    navController: NavController,
    productId: Int, // Recibimos el ID del producto
    shoppingCartViewModel: ShoppingCartViewModel,
    productViewModel: ProductViewModel = viewModel()
) {
    // Intentamos obtener el producto por su ID, si no lo encontramos, asignamos null
    val product = remember { productViewModel.getProductById(productId) }

    // Si no encontramos el producto, mostramos un mensaje de error
    if (product == null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Producto no encontrado",
            )
        }
        return
    }

    // Si encontramos el producto, continuamos con la UI normal
    var quantity by remember { mutableIntStateOf(1) }
    var expanded by remember { mutableStateOf(false) }
    val quantityOptions = (1..6).toList()

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(navController)

        Box(modifier = Modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.TopCenter) {
            Card(
                modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()).padding(16.dp)) {
                    // Nombre del producto
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        ),
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Imagen del producto
                    Image(
                        painter = painterResource(id = product.image),
                        contentDescription = "Imagen del producto",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Descripción del producto
                    Text(
                        text = product.description,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 16.sp,
                            color = Color.Gray
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Selector de cantidad
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(BorderStroke(1.dp, Color.Gray), RoundedCornerShape(8.dp))
                                .clickable { expanded = !expanded }
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = quantity.toString(),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = 20.sp
                                ),
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center // Centrar texto
                            )
                            // Icono de flecha
                            Icon(
                                imageVector = Icons.Filled.KeyboardArrowDown, // Flecha hacia abajo
                                contentDescription = "Expandir opciones",
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        // Mostrar menú desplegable
                        if (expanded) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White)
                                    .padding(8.dp)
                            ) {
                                quantityOptions.forEach { option ->
                                    Text(
                                        text = option.toString(),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp)
                                            .clickable {
                                                quantity = option
                                                expanded = false
                                            },
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontSize = 18.sp
                                        )
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Precio del producto
                    Text(
                        text = "$${product.price}",
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = BlueBackground,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Botón en la parte inferior
                    Button(
                        onClick = {
                            val shoppingItem = ShoppingModel(
                                id = product.id,
                                name = product.name,
                                description = product.description,
                                imageResId = product.image,
                                price = "$${product.price}",
                                selectedQuantity = quantity
                            )
                            shoppingCartViewModel.addProductToCart(shoppingItem)
                            navController.navigate("ShoppingCart")
                        },
                        modifier = Modifier
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = YellowIcons,
                            contentColor = Color.Red
                        )
                    ) {
                        Text(text = "Agregar al carrito", color = BlueBackground)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}
