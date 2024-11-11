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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toolxpress.R
import com.example.toolxpress.ui.components.TopBar
import com.example.toolxpress.ui.theme.Orange
import com.example.toolxpress.ui.theme.YellowIcons

@Composable
fun ComprasScreen(navController: NavController) {
    var quantity by remember { mutableStateOf(1) }
    var expanded by remember { mutableStateOf(false) }
    val quantityOptions = (1..6).toList()
    val unitPrice = 199.99
    val totalPrice = unitPrice * quantity

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(navController)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), // Reducido para dar más espacio al Card
            contentAlignment = Alignment.TopCenter
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.95f) // Aumenta el tamaño del cuadro
                    .padding(8.dp), // Menor padding para que ocupe más espacio
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(12.dp) // Aumentada la elevación
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp, vertical = 16.dp) // Aumentado el padding vertical
                ) {
                    // Imagen del producto
                    Image(
                        painter = painterResource(id = R.drawable.taladro),
                        contentDescription = "Imagen del producto",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp) // Aumentada la altura de la imagen
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Nombre del producto
                    Text(
                        text = "Taladro Profesional 800W con Percusión",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp // Aumentada la fuente
                        ),
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Descripción del producto
                    Text(
                        text = "Ideal para trabajos de construcción y reparación en casa. Potente motor de 800W con función de percusión para taladrar en superficies duras.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 18.sp, // Aumentada la fuente
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Sección de detalles
                    Text(
                        text = "Detalles del producto:",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp // Aumentada la fuente
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Text(
                        text = """
                            • Potencia: 800W
                            • Velocidad: hasta 3000 RPM
                            • Capacidad de perforación: 13 mm en hormigón
                            • Peso: 2.2 kg
                        """.trimIndent(),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 16.sp, // Aumentada la fuente
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Precio total según la cantidad
                    Text(
                        text = "Precio unitario: $199.99",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 16.sp,
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Text(
                        text = "Total: $${"%.2f".format(totalPrice)}",
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Color.Green,
                            fontSize = 26.sp, // Aumentada la fuente
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    // Detalles de envío y disponibilidad
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Llega mañana si compras en la próxima hora",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 16.sp, // Aumentada la fuente
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Text(
                        text = "Envío gratis",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 16.sp, // Aumentada la fuente
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Botón de compra
                    Button(
                        onClick = {
                            navController.navigate("EnvioScreen")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp), // Aumentada la altura del botón
                        colors = ButtonDefaults.buttonColors(
                            containerColor = YellowIcons,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Revisar Envio", color = Color.White)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}
