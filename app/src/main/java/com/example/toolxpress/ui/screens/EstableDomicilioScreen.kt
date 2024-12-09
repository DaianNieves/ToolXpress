package com.example.toolxpress.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Store
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.toolxpress.data.model.ShoppingCartViewModel
import com.example.toolxpress.ui.theme.YellowIcons
import com.example.toolxpress.ui.theme.BlueBackground
import com.example.toolxpress.ui.components.TopBar

@Composable
fun EstableDomicilioScreen(navController: NavController, shoppingCartViewModel: ShoppingCartViewModel = viewModel()) {
    // Estado para el seguimiento de la selección y el diálogo
    var selectedOption by remember { mutableStateOf<Option?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(navController)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Selecciona una Opción",
                    color = YellowIcons,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Opción "Enviar a domicilio"
                Card(
                    onClick = { selectedOption = Option.ENVIAR_DOMICILIO },
                    colors = CardDefaults.cardColors(
                        containerColor = if (selectedOption == Option.ENVIAR_DOMICILIO) YellowIcons else Color(0xFFE0E0E0)
                    ),
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .height(80.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Enviar a domicilio",
                            tint = if (selectedOption == Option.ENVIAR_DOMICILIO) BlueBackground else Color.Black,
                            modifier = Modifier.padding(end = 16.dp)
                        )
                        Column {
                            Text(
                                text = "Enviar a domicilio",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (selectedOption == Option.ENVIAR_DOMICILIO) BlueBackground else Color.Black
                            )
                            Text(
                                text = "Te enviaremos el pedido a tu dirección.",
                                fontSize = 14.sp,
                                color = if (selectedOption == Option.ENVIAR_DOMICILIO) Color.DarkGray else Color.Gray
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Opción "Recoger en establecimiento"
                Card(
                    onClick = { selectedOption = Option.RECOGER_ESTABLECIMIENTO },
                    colors = CardDefaults.cardColors(
                        containerColor = if (selectedOption == Option.RECOGER_ESTABLECIMIENTO) YellowIcons else Color(0xFFE0E0E0)
                    ),
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .height(80.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Store,
                            contentDescription = "Recoger en establecimiento",
                            tint = if (selectedOption == Option.RECOGER_ESTABLECIMIENTO) BlueBackground else Color.Black,
                            modifier = Modifier.padding(end = 16.dp)
                        )
                        Column {
                            Text(
                                text = "Recoger en establecimiento",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (selectedOption == Option.RECOGER_ESTABLECIMIENTO) BlueBackground else Color.Black
                            )
                            Text(
                                text = "Recoge tu pedido en nuestra tienda.",
                                fontSize = 14.sp,
                                color = if (selectedOption == Option.RECOGER_ESTABLECIMIENTO) Color.DarkGray else Color.Gray
                            )
                        }
                    }
                }

                // Botón Continuar
                Button(
                    onClick = {
                        if (selectedOption == Option.ENVIAR_DOMICILIO) {
                            navController.navigate("MetodoPagoScreen")
                            shoppingCartViewModel.completePurchase()
                            shoppingCartViewModel.clearCart() // Limpiar el carrito
                        } else if (selectedOption == Option.RECOGER_ESTABLECIMIENTO) {
                            showDialog = true
                        }
                    },
                    enabled = selectedOption != null,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = YellowIcons,
                        disabledContainerColor = Color.Gray,
                        contentColor = BlueBackground,
                        disabledContentColor = BlueBackground
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                        .height(60.dp)
                ) {
                    Text(text = "Continuar", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        // Diálogo de confirmación al seleccionar "Recoger en establecimiento"
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
                        "Solo tienen 48 horas para recoger y pagar en tienda. Por favor acuda a la sucursal sur",
                        fontSize = 18.sp,
                        color = BlueBackground,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog = false
                            shoppingCartViewModel.completePurchase()
                            shoppingCartViewModel.clearCart() // Limpiar el carrito
                            navController.navigate("StartScreen") // Navegar a la pantalla de inicio
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = YellowIcons,
                            contentColor = BlueBackground
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text("Aceptar", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                }
                ,
                properties = DialogProperties(),
                modifier = Modifier
                    .background(BlueBackground, shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp))
                    .padding(24.dp)
                    .fillMaxWidth(0.8f)
            )
        }
    }
}

// Enum para las opciones seleccionables
enum class Option {
    ENVIAR_DOMICILIO,
    RECOGER_ESTABLECIMIENTO
}
