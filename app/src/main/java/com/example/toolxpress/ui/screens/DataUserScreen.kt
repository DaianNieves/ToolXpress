package com.example.toolxpress.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toolxpress.ui.theme.Orange
import com.example.toolxpress.ui.components.TopBar

@Composable
fun DataUserScreen(navController: NavController) {
    // Columna principal que contiene la barra superior y el contenido
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Barra superior separada del resto del contenido
        TopBar(navController)

        // Contenedor principal para el contenido de la pantalla
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 0.dp)
        ) {
            // Agregamos scroll al contenido principal
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()) // Habilitar scroll vertical
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.Top
            ) {
                // Título principal
                Text(
                    text = "Datos de tu cuenta",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Sección de E-mail
                AccountInfoCard(
                    title = "E-mail",
                    initialValue = "daianieves10@gmail.com",
                    isVerified = true,
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Sección de Teléfono
                AccountInfoCard(
                    title = "Teléfono",
                    initialValue = "+524493134220",
                    isVerified = true,
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Sección de Nombre de usuario
                AccountInfoCard(
                    title = "Nombre de usuario",
                    initialValue = "NIEVESDAIÁN20230403051727",
                    isVerified = false,
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Otros campos como Código postal, Estado, Municipio/Alcaldía, Colonia y Calle
                AccountInfoCard(
                    title = "Código postal",
                    initialValue = "12345",
                    isVerified = false,
                )

                Spacer(modifier = Modifier.height(8.dp))

                AccountInfoCard(
                    title = "Estado",
                    initialValue = "Tu estado",
                    isVerified = false,
                )

                Spacer(modifier = Modifier.height(8.dp))

                AccountInfoCard(
                    title = "Municipio/Alcaldía",
                    initialValue = "Tu municipio",
                    isVerified = false,
                )

                Spacer(modifier = Modifier.height(8.dp))

                AccountInfoCard(
                    title = "Colonia",
                    initialValue = "Tu colonia",
                    isVerified = false,
                )

                Spacer(modifier = Modifier.height(8.dp))

                AccountInfoCard(
                    title = "Calle",
                    initialValue = "Tu calle",
                    isVerified = false,
                )
            }
        }
    }
}

@Composable
fun AccountInfoCard(title: String, initialValue: String, isVerified: Boolean) {
    var isEditing by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf(initialValue) } // Estado para el valor actual

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Título y verificación en una sola fila
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
                if (isVerified) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Validado",
                        tint = Color(0xFF4CAF50) // Color verde para verificado
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Mostrar campo de texto o texto normal
            if (isEditing) {
                OutlinedTextField(
                    value = value,
                    onValueChange = { value = it }, // Actualizar el valor
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    label = { Text("Modificar $title") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { isEditing = false }, // Salir del modo de edición
                    colors = ButtonDefaults.buttonColors(containerColor = Orange)
                ) {
                    Text("Guardar")
                }
            } else {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = value,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                    TextButton(onClick = { isEditing = true }) {
                        Text(
                            text = "Modificar",
                            color = Orange,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

