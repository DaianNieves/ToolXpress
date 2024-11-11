package com.example.toolxpress.ui.screens

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
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
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavController
import com.example.toolxpress.ui.theme.BlueBackground
import com.example.toolxpress.ui.theme.YellowIcons
import kotlinx.coroutines.delay

@Composable
fun EnvioScreen(navController: NavController) {
    var sliderPosition by remember { mutableStateOf(0f) }
    val context = LocalContext.current

    // Solicitar permisos si es necesario
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (!isGranted) {
            // Manejar caso en que el permiso no fue concedido
        }
    }

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    // Crear el canal de notificación al iniciar la pantalla
    LaunchedEffect(Unit) {
        createNotificationChannel(context)
    }

    // Simular progreso automáticamente y enviar notificaciones
    LaunchedEffect(Unit) {
        while (sliderPosition < 1f) {
            delay(9000) // Espera 9 segundos
            sliderPosition += 0.5f // Avanza el progreso en un 50%

            when (sliderPosition) {
                0f -> sendNotification(context, "Preparación", "Tu pedido está en preparación.")
                0.5f -> sendNotification(context, "Pedido en Camino", "Tu pedido ha salido para la entrega.")
                1f -> sendNotification(context, "Pedido Entregado", "Tu pedido ha sido entregado exitosamente.")
            }
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
                progress = sliderPosition,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                color = YellowIcons
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
        
        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            "envio_channel",
            "Notificaciones de Envío",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Canal para notificaciones de estado de envío"
        }

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

fun sendNotification(context: Context, title: String, message: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
        ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        return
    }

    val notification = NotificationCompat.Builder(context, "envio_channel")
        .setSmallIcon(android.R.drawable.ic_dialog_info)
        .setContentTitle(title)
        .setContentText(message)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setAutoCancel(true)
        .build()

    NotificationManagerCompat.from(context).notify(System.currentTimeMillis().toInt(), notification)
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
            tint = if (isActive) YellowIcons else Color.White,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            color = if (isActive) YellowIcons else Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}
@Composable
fun OrderStatusItem(status: String, date: String, description: String, isActive: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isActive) Color.White else Color.White, shape = RoundedCornerShape(8.dp))
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
