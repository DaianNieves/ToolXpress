package com.example.toolxpress.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun EnvioScreen(navController: NavController) {

    // Estado inicial
    var sliderPosition by remember { mutableStateOf(0f) }

    // Actualizar texto
    val deliveryStatusText = when (sliderPosition) {
        0f -> "Preparando envío"
        0.5f -> "En camino"
        1f -> "Entregado"
        else -> "Preparando envío"
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Regresar",
                modifier = Modifier
                    .size(28.dp)
                    .clickable { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Seguimiento de Envío",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(200.dp))
        // Texto estado de envio
        Text(
            text = deliveryStatusText,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(100.dp))

        // Slider con 3 estados
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 0f..1f, // Solo tres estados: 0, 0.5, y 1
            steps = 1, // Hace que haya dos puntos intermedios
            modifier = Modifier.padding(horizontal = 80.dp)
        )

        Spacer(modifier = Modifier.height(100.dp))

        when (sliderPosition) {
            0f -> {
                Icon(
                    imageVector = Icons.Filled.Build,
                    contentDescription = "En preparación",
                    modifier = Modifier.size(64.dp),
                    tint = MaterialTheme.colorScheme.scrim
                )
                Text(
                    text = "En preparación",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    textAlign = TextAlign.Center
                )
            }

            0.5f -> {
                Icon(
                    imageVector = Icons.Filled.LocalShipping, // Icono de en camino
                    contentDescription = "En camino",
                    modifier = Modifier.size(64.dp),
                    tint = MaterialTheme.colorScheme.scrim
                )
                Text(
                    text = "En camino",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.Green),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 30.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Tu paquete está en el último tramo del recorrido.",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    textAlign = TextAlign.Center
                )
            }

            1f -> {
                Icon(
                    imageVector = Icons.Filled.CheckCircle, // Icono de entregado
                    contentDescription = "Entregado",
                    modifier = Modifier.size(64.dp),
                    tint = Color.Gray
                )
                Text(
                    text = "Entregado",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
