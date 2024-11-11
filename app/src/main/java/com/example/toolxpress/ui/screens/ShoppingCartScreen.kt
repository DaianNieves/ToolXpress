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
import com.example.toolxpress.ui.theme.BlueBackground
import com.example.toolxpress.ui.theme.GrayProduct
import com.example.toolxpress.ui.theme.YellowIcons

@Composable
fun ShoppingCartScreen(navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }
    var productList by remember {
        mutableStateOf(
            arrayListOf(
                ShoppingModel(
                    7,
                    "Taladro Inalámbrico",
                    "NANWEI Kit de Taladro Inalámbrico Electrico",
                    R.drawable.taladro,
                    "$594.00"
                ),
                ShoppingModel(
                    8,
                    "Pulidora inalámbrica",
                    "Esmeriladora Angular Pulidora Inalambrica Con Accesorios",
                    R.drawable.pulidora,
                    "$799.00"
                ),
                ShoppingModel(
                    2,
                    "Kit desarmador",
                    "Juego P/reparación De Celulares Y Disp. Electrónicos,77 Pzas",
                    R.drawable.desarmador,
                    "$295.00"
                ),
                ShoppingModel(
                    10,
                    "Pistola de calor",
                    "RexQualis de 2000w Temperatura Regulable 4 Boquillas",
                    R.drawable.pistolacalor,
                    "$384.00"
                ),
                ShoppingModel(
                    1,
                    "Engrapadora",
                    "Engrapadora Tipo Pistola Para Tapiceria Con 3000 Grapas",
                    R.drawable.engrapadora,
                    "$188.00"
                ),
                ShoppingModel(
                    5,
                    "Pinza de presión",
                    "Pinza Presión 10' Mordaza Recta Pretul Granel Pretul 2270",
                    R.drawable.pinza,
                    "$94.00"
                ),
                ShoppingModel(
                    6,
                    "Escalera Tubular",
                    "Escalera Tubular, Plegable, 2 Peldaños, Pretul Pretul 24118",
                    R.drawable.escaleras,
                    "$595.00"
                ),
                ShoppingModel(
                    4,
                    "Martillo Uña Recta",
                    "Martillo Uña Recta, 16oz, Mango Fibra De Vidrio Truper 19997",
                    R.drawable.martillo,
                    "$149.00"
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
                            containerColor = YellowIcons,
                        )
                    ) {
                        Text(
                            "Comprar",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = BlueBackground
                        )
                    }

                    Button(
                        onClick = { showDialog = true },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = YellowIcons,
                        )
                    ) {
                        Text(
                            text = "Apartar Carrito",
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

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    "Productos apartados",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
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
                        color = BlueBackground
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Button(
                    onClick = { showDialog = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = YellowIcons,
                        contentColor = BlueBackground
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
                .background(BlueBackground, shape = RoundedCornerShape(16.dp))
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
