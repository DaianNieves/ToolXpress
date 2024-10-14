package com.example.toolxpress.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.navigation.NavController
import com.example.toolxpress.R
import com.example.toolxpress.ui.theme.GreyProduct
import com.example.toolxpress.ui.theme.Orange
import com.example.toolxpress.ui.theme.components.TopBar


@Composable
fun EnvioScreen(navController: NavController) {
    var sliderPosition by remember { mutableStateOf(0f) } // Estado inicial del progreso

    // Simular progreso automáticamente usando LaunchedEffect
    LaunchedEffect(Unit) {
        // Simula el cambio del estado del pedido en el tiempo
        while (sliderPosition < 1f) {
            delay(9000) // Espera 9 segundos
            sliderPosition += 0.5f // Avanza el progreso en un 50%
        }
    }

    Box {
        TopBar(navController)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        // Recuadro gris para el título "Status"
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(GreyProduct, shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "Status",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(50.dp))

        // Indicador de progreso lineal en la parte superior
        LinearProgressIndicator(
            progress = sliderPosition,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            color = Orange
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Estado de envío (íconos que se iluminan según el progreso)
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

        Spacer(modifier = Modifier.height(24.dp))

        // Proceso de entrega (lista de eventos)
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            OrderStatusItem(
                status = "Pagado",
                date = "5 Oct",
                description = "Pedido pagado exitosamente.",
                isActive = sliderPosition >= 0f
            )
            Spacer(modifier = Modifier.height(8.dp)) // Espacio en blanco entre los elementos
            OrderStatusItem(
                status = "Empaquetado",
                date = "6 Oct",
                description = "El pedido ha sido empacado y está listo para ser enviado.",
                isActive = sliderPosition >= 0.5f
            )
            Spacer(modifier = Modifier.height(8.dp)) // Espacio en blanco entre los elementos
            OrderStatusItem(
                status = "Enviado",
                date = "7 Oct",
                description = "Tu pedido está en camino.",
                isActive = sliderPosition >= 0.5f
            )
            Spacer(modifier = Modifier.height(8.dp)) // Espacio en blanco entre los elementos
            OrderStatusItem(
                status = "Entregado",
                date = "8 Oct",
                description = "El pedido ha sido entregado.",
                isActive = sliderPosition == 1f
            )
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