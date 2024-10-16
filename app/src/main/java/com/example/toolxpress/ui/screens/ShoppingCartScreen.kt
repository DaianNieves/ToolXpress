package com.example.toolxpress.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var showDialog by remember { mutableStateOf(false) } // Estado del mensaje emergente

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(navController = navController)
        // Usamos un LazyColumn para permitir el desplazamiento
        LazyColumn {

            val productList = arrayOf(
                ShoppingModel(1, "Producto 1", "Descripción del producto 1", R.drawable.ejemploimagen, "$10.00"),
                ShoppingModel(2, "Producto 2", "Descripción del producto 2", R.drawable.ejemploimagen, "$15.00"),
                ShoppingModel(3, "Producto 3", "Descripción del producto 3", R.drawable.ejemploimagen, "$20.00"),
                ShoppingModel(4, "Producto 4", "Descripción del producto 4", R.drawable.ejemploimagen, "$25.00")
            )

            items(productList) { product ->
                ProductItem(product)
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp) // Espacio uniforme entre los botones
                ) {
                    // Botón "Comprar"
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
                            textAlign = TextAlign.Center, // Centrado del texto
                            color = Color.White // Color del texto en blanco
                        )
                    }

                    // Botón "Apartar Carrito"
                    Button(
                        onClick = { showDialog = true }, // Mostrar el mensaje emergente
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
                            textAlign = TextAlign.Center, // Centrado del texto
                            color = Color.White // Color del texto en blanco
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

            }
        }
    }

    // Mensaje emergente
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    "Productos apartados",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center, // Centrar el texto del título
                    modifier = Modifier.fillMaxWidth() // Llenar el ancho
                )
            },
            text = {
                Text(
                    "Solo tienen 48 horas para recoger y pagar en tienda.",
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 18.sp,
                        color = Color.Gray // Color del texto del mensaje
                    ),
                    textAlign = TextAlign.Center, // Centrar el texto
                    modifier = Modifier.fillMaxWidth() // Llenar el ancho
                )
            },
            confirmButton = {
                Button(
                    onClick = { showDialog = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Orange, // Color del botón
                        contentColor = Color.White // Color del texto del botón
                    ),
                    shape = RoundedCornerShape(20.dp), // Esquinas redondeadas del botón
                    modifier = Modifier
                        .fillMaxWidth() // Llenar el ancho
                        .padding(horizontal = 16.dp, vertical = 8.dp) // Espaciado del botón
                ) {
                    Text(
                        "Aceptar",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp // Tamaño del texto del botón
                    )
                }
            },
            properties = DialogProperties(),
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(16.dp)) // Esquinas redondeadas
                .padding(24.dp) // Espaciado adicional para el contenido
                .fillMaxWidth(0.8f) // Ancho del diálogo
        )
    }
}

@Composable
fun ProductItem(product: ShoppingModel) {
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

                    Row {
                        TextButton(onClick = {}) {
                            Text("Guardar", color = Orange, fontSize = 16.sp)
                        }
                        TextButton(onClick = {}) {
                            Text("Eliminar", color = Orange, fontSize = 16.sp)
                        }
                    }

                    var expanded by remember { mutableStateOf(false) }
                    var selectedOption by remember { mutableStateOf("Selecciona cantidad") }

                    Box {
                        TextButton(onClick = { expanded = true }) {
                            Text(selectedOption, fontSize = 16.sp)
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            listOf("1", "2", "3", "4", "5", "6").forEach { option ->
                                DropdownMenuItem(onClick = {
                                    selectedOption = option
                                    expanded = false
                                }) {
                                    Text(option)
                                }
                            }
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
                Text(product.price, fontSize = 18.sp, color = GreenPrice)
            }
        }
    }
}
