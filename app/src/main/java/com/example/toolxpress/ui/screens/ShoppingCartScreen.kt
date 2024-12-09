package com.example.toolxpress.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toolxpress.data.model.ShoppingCartViewModel
import com.example.toolxpress.ui.theme.GreenPrice
import com.example.toolxpress.ui.theme.Orange
import com.example.toolxpress.ui.components.TopBar
import com.example.toolxpress.data.model.ShoppingModel
import com.example.toolxpress.ui.theme.BlueBackground
import com.example.toolxpress.ui.theme.GrayProduct
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import com.example.toolxpress.ui.theme.YellowIcons

@Composable
fun ShoppingCartScreen(navController: NavController, shoppingCartViewModel: ShoppingCartViewModel) {
    val productList = shoppingCartViewModel.shoppingCart
    var totalAmount by remember { mutableStateOf(0.0) }

    // Calcular el total actualizado
    totalAmount = shoppingCartViewModel.calculateTotal()

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(navController = navController)

        // Mostrar mensaje si el carrito está vacío
        if (productList.isEmpty()) {
            // Mostrar ícono de carrito y mensaje "Carrito Vacío"
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "Carrito Vacío",
                        modifier = Modifier.size(100.dp),
                        tint = YellowIcons // Color amarillo para el ícono
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Carrito Vacío",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = YellowIcons // Texto de color amarillo
                    )
                }
            }
        } else {
            // Mostrar productos en el carrito
            LazyColumn(
                modifier = Modifier
                    .weight(1f) // El LazyColumn ocupa el espacio disponible
                    .padding(bottom = 100.dp) // Añadimos padding para no cubrir el total y botón
            ) {
                items(productList) { product ->
                    ProductItem(
                        product = product,
                        onRemove = {
                            shoppingCartViewModel.removeProductFromCart(product.id)
                        },
                        onQuantityChange = { newQuantity ->
                            shoppingCartViewModel.updateProductQuantity(product.id, newQuantity)
                            totalAmount = shoppingCartViewModel.calculateTotal()
                        }
                    )
                }
            }

            // Mostrar el total solo si hay productos
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = GrayProduct,
                elevation = 4.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Total:",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        "$${"%.2f".format(totalAmount)}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = YellowIcons
                    )
                }
            }

            // Fijar el botón de compra al fondo
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Button(
                    onClick = { navController.navigate("EstableDomicilioScreen") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = YellowIcons),
                    enabled = productList.isNotEmpty()
                ) {
                    Text(
                        "Comprar",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = BlueBackground
                    )
                }
            }
        }
    }
}

@Composable
fun ProductItem(
    product: ShoppingModel,
    onRemove: () -> Unit,
    onQuantityChange: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        elevation = 4.dp
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = product.imageResId),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(100.dp)
                )

                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(product.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(product.description, fontSize = 14.sp)

                    Box {
                        TextButton(onClick = { /* Mostrar opciones de cantidad */ }) {
                            Text("Cantidad: ${product.selectedQuantity}", fontSize = 16.sp)
                        }
                        DropdownMenu(
                            expanded = false, // Implementar lógica de expandir
                            onDismissRequest = { }
                        ) {
                            listOf(1, 2, 3, 4, 5, 6).forEach { option ->
                                DropdownMenuItem(onClick = {
                                    onQuantityChange(option)
                                }) {
                                    Text(option.toString())
                                }
                            }
                        }
                    }

                    Row {
                        TextButton(onClick = onRemove) {
                            Text("Eliminar del Carrito", color = GreenPrice, fontSize = 16.sp)
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 40.dp, bottom = 20.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text("Subtotal: ", fontSize = 18.sp)
                val updatedPrice = (product.price.replace("$", "").toDoubleOrNull() ?: 0.0) * product.selectedQuantity
                Text("$${"%.2f".format(updatedPrice)}", fontSize = 18.sp, color = BlueBackground)
            }
        }
    }
}
