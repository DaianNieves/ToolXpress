package com.example.toolxpress.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import com.example.toolxpress.notifications.NotificationManagerSingleton
import com.example.toolxpress.ui.theme.BlueBackground
import com.example.toolxpress.ui.theme.YellowIcons

@Composable
fun EnvioScreen(navController: NavController) {
    val context = LocalContext.current
    val progress by NotificationManagerSingleton.progress.collectAsState()

    // Solicitar permisos si es necesario
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (!isGranted) {
            // Manejar caso en que el permiso no fue concedido
        }
    }

    LaunchedEffect(Unit) {
        // Solicitar permisos de notificaciones si es necesario
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }

        // Iniciar las notificaciones globales si no están corriendo
        NotificationManagerSingleton.startProgressNotifications(context)
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
                    .background(YellowIcons)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = BlueBackground,
                    modifier = Modifier.clickable {
                        navController.navigate("StartScreen")
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Estado de la compra",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = BlueBackground
                )
            }
        }

        item { Spacer(modifier = Modifier.height(50.dp)) }

        // Indicador de progreso
        item {
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                color = YellowIcons
            )
        }

        item { Spacer(modifier = Modifier.height(24.dp)) }

        // Estado del envío (ajusta los iconos según el progreso)
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProgressIcon(
                    isActive = progress >= 0f,
                    icon = Icons.Default.Build,
                    label = "Preparación"
                )
                ProgressIcon(
                    isActive = progress >= 0.5f,
                    icon = Icons.Default.LocalShipping,
                    label = "En camino"
                )
                ProgressIcon(
                    isActive = progress == 1f,
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
                isActive = progress >= 0f
            )
        }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        item {
            OrderStatusItem(
                status = "Empaquetado",
                date = "6 Oct",
                description = "El pedido ha sido empacado y está listo para ser enviado.",
                isActive = progress >= 0.5f
            )
        }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        item {
            OrderStatusItem(
                status = "Enviado",
                date = "7 Oct",
                description = "Tu pedido está en camino.",
                isActive = progress >= 0.5f
            )
        }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        item {
            OrderStatusItem(
                status = "Entregado",
                date = "8 Oct",
                description = "El pedido ha sido entregado.",
                isActive = progress == 1f
            )
        }

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
            tint = if (isActive) YellowIcons else Color.Gray,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            color = if (isActive) YellowIcons else Color.Gray,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun OrderStatusItem(status: String, date: String, description: String, isActive: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (isActive) Color.White else Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = status, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = date, color = Color.Black, fontSize = 14.sp)
            Text(text = description, color = Color.Black, fontSize = 14.sp)
        }
        if (isActive) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Check",
                tint = YellowIcons,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
