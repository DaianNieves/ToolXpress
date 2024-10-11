package com.example.toolxpress.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toolxpress.R
import com.example.toolxpress.ui.theme.data.model.ShoppingModel

@Composable
fun ShoppingCartScreen(navController: NavController) {

    // Declaración de Variables
    var ColorProd = Color(0xFF2c2c2c)
    var ColorTextAzul = Color(color = 0xFF0057c4)
    var ColorTextPrecio = Color(color = 0xFF1abf00)

    // Contenedor Principal
    Box(
        modifier = Modifier
            .padding(0.dp, 80.dp, 0.dp, 0.dp)
    ) {
        Column {

            Row(
                // Barra Superior de Productos
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp,
                            bottomStart = 16.dp,
                            bottomEnd = 16.dp
                        )
                    )
                    .background(ColorProd)
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 36.dp, end = 24.dp), // Ajuste de margen
                    text = "Producto",
                    textAlign = TextAlign.Left,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            val productList = arrayOf(
                ShoppingModel(1, "Producto 1", "Descripción del producto 1", R.drawable.ejemploimagen, "$10.00"),
                ShoppingModel(2, "Producto 2", "Descripción del producto 2", R.drawable.ejemploimagen, "$15.00"),
                ShoppingModel(3, "Producto 3", "Descripción del producto 3", R.drawable.ejemploimagen, "$20.00"),
                ShoppingModel(4, "Producto 4", "Descripción del producto 4", R.drawable.ejemploimagen, "$25.00")
            )

            LazyColumn {
                items(productList) { product ->
                    Column(modifier = Modifier
                        .fillMaxWidth()) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Image(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(100.dp),
                                painter = painterResource(id = product.imageResId),
                                contentDescription = "",
                                contentScale = ContentScale.Crop
                            )

                            Column(modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp)) {
                                Text(text = product.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Text(text = product.description, fontSize = 14.sp)
                                Row {
                                    TextButton(onClick = { /* Acción para Guardar */ }) {
                                        Text("Guardar", color = ColorTextAzul)
                                    }
                                    TextButton(onClick = { /* Acción para Eliminar */ }) {
                                        Text("Eliminar", color = ColorTextAzul)
                                    }
                                }

                                // Menú desplegable para seleccionar la cantidad de productos
                                var expanded by remember { mutableStateOf(false) }
                                var selectedOption by remember { mutableStateOf("Selecciona cantidad") }

                                Box {
                                    TextButton(onClick = { expanded = true }) {
                                        Text(selectedOption)
                                    }
                                    DropdownMenu(
                                        expanded = expanded,
                                        onDismissRequest = { expanded = false }
                                    ) {
                                        listOf("1 producto", "2 productos", "3 productos", "4 productos").forEach { option ->
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
                                .padding(0.dp, 0.dp, 40.dp, 0.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(text = "Subtotal: ", fontSize = 18.sp)
                            Text(text = "${product.price}", fontSize = 18.sp, color = ColorTextPrecio)
                        }
                    }
                }
            }

            // Botón "Comprar"
            Button(
                onClick = { /* Acción para Comprar */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Comprar")
            }

            // Botón "Seguir Explorando"
            Button(
                onClick = { /* Acción para Seguir Explorando */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Seguir Explorando")
            }

        }
    }



    //Contenedor del Footer
    Box() {

    }
}