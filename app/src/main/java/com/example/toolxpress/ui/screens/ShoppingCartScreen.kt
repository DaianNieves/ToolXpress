package com.example.toolxpress.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.toolxpress.R
import com.example.toolxpress.ui.theme.GreenPrice
import com.example.toolxpress.ui.theme.Orange
import com.example.toolxpress.ui.components.TopBar
import com.example.toolxpress.data.model.ShoppingModel

@Composable
fun ShoppingCartScreen(navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }
    var productList by remember {
        mutableStateOf(
            arrayListOf(
                ShoppingModel(
                    1,
                    "Producto 1",
                    "Descripción del producto 1",
                    R.drawable.ejemploimagen,
                    "$10.00"
                ),
                ShoppingModel(
                    2,
                    "Producto 2",
                    "Descripción del producto 2",
                    R.drawable.ejemploimagen,
                    "$15.00"
                ),
                ShoppingModel(
                    3,
                    "Producto 3",
                    "Descripción del producto 3",
                    R.drawable.ejemploimagen,
                    "$20.00"
                ),
                ShoppingModel(
                    4,
                    "Producto 4",
                    "Descripción del producto 4",
                    R.drawable.ejemploimagen,
                    "$25.00"
                )
            )
        )
    }

    var totalAmount by remember { mutableStateOf(0.0) }

    // Calcular el total actualizado
    totalAmount = productList.sumOf { product ->
        (product.price.replace("$", "").toDoubleOrNull() ?: 0.0) * product.selectedQuantity
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(navController = navController)

        // Usamos una LazyColumn para hacer scroll en toda la pantalla
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(productList) { product ->
                ProductItem(
                    product = product,
                    onRemove = {
                        productList.removeIf { it.id == product.id }
                    },
                    onQuantityChange = { newQuantity ->
                        product.selectedQuantity = newQuantity
                        totalAmount = productList.sumOf { p ->
                            (p.price.replace("$", "").toDoubleOrNull() ?: 0.0) * p.selectedQuantity
                        }
                    }
                )
            }

            // Card que muestra el total
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = Color.White,
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
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "$${"%.2f".format(totalAmount)}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = GreenPrice
                        )
                    }
                }
            }

            // Contenedor de botones
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { navController.navigate("DomicilioScreen") },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Orange,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            "Comprar",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }

                    Button(
                        onClick = { showDialog = true },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Orange,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = "Apartar Carrito",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    "Productos apartados",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            text = {
                Text(
                    "Solo tienen 48 horas para recoger y pagar en tienda.",
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 18.sp,
                        color = Color.Gray
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Button(
                    onClick = { showDialog = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Orange,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        "Aceptar",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            },
            properties = DialogProperties(),
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(24.dp)
                .fillMaxWidth(0.8f)
        )
    }
}

@Composable
fun ProductItem(
    product: ShoppingModel,
    onRemove: () -> Unit,
    onQuantityChange: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(1) }

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
                        TextButton(onClick = { expanded = true }) {
                            Text("Cantidad: $selectedOption", fontSize = 16.sp)
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            listOf(1, 2, 3, 4, 5, 6).forEach { option ->
                                DropdownMenuItem(onClick = {
                                    selectedOption = option
                                    onQuantityChange(option)
                                    expanded = false
                                }) {
                                    Text(option.toString())
                                }
                            }
                        }
                    }

                    Row {
                        TextButton(onClick = onRemove) {
                            Text("Eliminar del Carrito", color = Orange, fontSize = 16.sp)
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
                val updatedPrice =
                    (product.price.replace("$", "").toDoubleOrNull() ?: 0.0) * selectedOption
                Text("$${"%.2f".format(updatedPrice)}", fontSize = 18.sp, color = GreenPrice)
            }
        }
    }
}
