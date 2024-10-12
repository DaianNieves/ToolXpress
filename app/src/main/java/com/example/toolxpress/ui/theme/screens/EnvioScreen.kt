package com.example.toolxpress.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material.icons.filled.ElectricRickshaw
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toolxpress.R

@Composable
fun EnvioScreen(navController: NavController) {
    var trackingNumber by remember { mutableStateOf("") } // Estado para el número de rastreo

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEEEEEE))
            .padding(16.dp)
    ) {
        // Menú superior
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                tint = Color.White,
                modifier = Modifier.clickable {
                    // Acción del menú
                }
            )
            Text(
                text = "Compras",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Carrito de compras",
                tint = Color.White,
                modifier = Modifier.clickable {
                    // Acción del carrito
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Estado de envío (con íconos)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Money,
                contentDescription = "Pagar",
                modifier = Modifier.size(28.dp),
                tint = Color.Black
            )
            Icon(
                imageVector = Icons.Default.ElectricRickshaw,
                contentDescription = "Enviado",
                modifier = Modifier.size(28.dp),
                tint = Color.Black
            )
            Icon(
                imageVector = Icons.Default.DeliveryDining,
                contentDescription = "Enviado",
                modifier = Modifier.size(40.dp),
                tint = Color.Black
            )
            Icon(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Entregado",
                modifier = Modifier.size(40.dp),
                tint = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Proceso de entrega (lista de eventos)
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            OrderStatusItem(
                status = "Pagado",
                date = "5 Oct",
                description = "Pedido pagado exitosamente."
            )
            OrderStatusItem(
                status = "Empacado",
                date = "6 Oct",
                description = "El pedido ha sido empacado y está listo para ser enviado."
            )
            OrderStatusItem(
                status = "Enviado",
                date = "7 Oct",
                description = "Tu pedido está en camino."
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Sección de rastreo
        Text(
            text = "Rastreo del Producto",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Campo de texto para el número de rastreo
        OutlinedTextField(
            value = trackingNumber,
            onValueChange = { trackingNumber = it },
            label = { Text("Número de rastreo") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Botón para rastrear el pedido
        Button(
            onClick = {
                // Acción para rastrear el producto (consultar el estado en base al número de rastreo)
                // Aquí iría la lógica para consultar el servicio de rastreo del producto
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFe66410))
        ) {
            Text(
                text = "Rastrear Pedido",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun OrderStatusItem(status: String, date: String, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.logo), // Reemplaza según el estado
            contentDescription = status,
            modifier = Modifier.size(48.dp),
            tint = Color.Gray
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = status, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = date, color = Color.Gray, fontSize = 14.sp)
            Text(text = description, color = Color.Black, fontSize = 14.sp)
        }
    }
}