package com.example.toolxpress.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.navigation.NavController
import com.example.toolxpress.ui.theme.Orange


@Composable
fun EnvioScreen(navController: NavController) {
    var sliderPosition by remember { mutableStateOf(0f) }

    // Simular progreso automáticamente
    LaunchedEffect(Unit) {
        while (sliderPosition < 1f) {
            delay(9000) // Espera 9 segundos
            sliderPosition += 0.5f // Avanza el progreso en un 50%
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()

    ) {
        // Barra superior
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Orange)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        navController.navigate("ComprasScreen")
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Estado de la compra",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        item { Spacer(modifier = Modifier.height(50.dp)) }

        // Indicador de progreso
        item {
            LinearProgressIndicator(
                progress = sliderPosition,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                color = Orange
            )
        }

        item { Spacer(modifier = Modifier.height(24.dp)) }

        // Estado del envío
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProgressIcon(
                    isActive = sliderPosition >= 0f,
                    icon = Icons.Default.Build,
                    label = "Preparación"
                )
                ProgressIcon(
                    isActive = sliderPosition >= 0.5f,
                    icon = Icons.Default.LocalShipping,
                    label = "En camino"
                )
                ProgressIcon(
                    isActive = sliderPosition == 1f,
                    icon = Icons.Default.CheckCircle,
                    label = "Entregado"
                )
            }
        }

        item { Spacer(modifier = Modifier.height(24.dp)) }

        // Lista de eventos del pedido
        item {
            OrderStatusItem(
                status = "Pagado",
                date = "5 Oct",
                description = "Pedido pagado exitosamente.",
                isActive = sliderPosition >= 0f
            )
        }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        item {
            OrderStatusItem(
                status = "Empaquetado",
                date = "6 Oct",
                description = "El pedido ha sido empacado y está listo para ser enviado.",
                isActive = sliderPosition >= 0.5f
            )
        }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        item {
            OrderStatusItem(
                status = "Enviado",
                date = "7 Oct",
                description = "Tu pedido está en camino.",
                isActive = sliderPosition >= 0.5f
            )
        }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        item {
            OrderStatusItem(
                status = "Entregado",
                date = "8 Oct",
                description = "El pedido ha sido entregado.",
                isActive = sliderPosition == 1f
            )
        }

        // Spacer al final para proporcionar espacio adicional
        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
fun ProgressIcon(isActive: Boolean, icon: ImageVector, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isActive) Orange else Color.Gray,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            color = if (isActive) Orange else Color.Gray,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun OrderStatusItem(status: String, date: String, description: String, isActive: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isActive) Color.LightGray else Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
            .padding(vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = status, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = date, color = Color.Black, fontSize = 14.sp)
            Text(text = description, color = Color.Black, fontSize = 14.sp)
        }
        // Mostrar ícono de check si está activo
        if (isActive) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Check",
                tint = Orange,
                modifier = Modifier.size(24.dp) // Ajusta el tamaño del ícono según sea necesario
            )
        }
    }
}