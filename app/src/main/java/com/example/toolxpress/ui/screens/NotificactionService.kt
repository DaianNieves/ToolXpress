package com.example.toolxpress.notifications

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

object NotificationManagerSingleton {
    private var isInitialized = false
    private var isRunning = false

    // Flujo de progreso
    private val _progress = MutableStateFlow(0f)
    val progress: StateFlow<Float> get() = _progress

    fun initialize(context: Context) {
        if (!isInitialized) {
            createNotificationChannel(context)
            isInitialized = true
        }
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "envio_channel",
                "Notificaciones de Envío",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Canal para notificaciones de estado de envío"
            }

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
            notificationManager?.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(context: Context, title: String, message: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED
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

    fun startProgressNotifications(context: Context) {
        if (isRunning) return // Evitar múltiples ejecuciones
        isRunning = true

        CoroutineScope(Dispatchers.Default).launch {
            while (_progress.value < 1f) {
                delay(9000) // Espera 9 segundos
                _progress.value += 0.5f

                when (_progress.value) {
                    0f -> sendNotification(context, "Preparación", "Tu pedido está en preparación.")
                    0.5f -> sendNotification(context, "Pedido en Camino", "Tu pedido ha salido para la entrega.")
                    1f -> sendNotification(context, "Pedido Entregado", "Tu pedido ha sido entregado exitosamente.")
                }
            }

            // Reiniciar el progreso al completarse
            _progress.value = 0f
            isRunning = false
        }
    }
}