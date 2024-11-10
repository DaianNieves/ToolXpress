package com.example.toolxpress.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
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
import com.example.toolxpress.ui.theme.BlueBackground
import com.example.toolxpress.ui.theme.YellowIcons

@Composable
fun DataUserScreen(navController: NavController) {
    var isUserDataExpanded by remember { mutableStateOf(false) }
    var isAddressDataExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(navController)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Datos de tu cuenta",
                    color = YellowIcons,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Card contenedor para los botones de "Datos del Usuario" y "Direcciones" con sombra
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(8.dp), // Sombra agregada al contenedor
                    colors = CardDefaults.cardColors(containerColor = Color.White) // Fondo blanco para el contenedor
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        // Texto clickeable para Datos del Usuario
                        TextButton(
                            onClick = {
                                isUserDataExpanded = !isUserDataExpanded
                                // Si se abre los datos del usuario, se cierra la dirección
                                if (isAddressDataExpanded) isAddressDataExpanded = false
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent) // Sin fondo
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Filled.Person,
                                    contentDescription = "Datos del Usuario",
                                    tint = BlueBackground,
                                    modifier = Modifier.size(40.dp) // Aumentamos el tamaño del ícono
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Column(
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Datos del Usuario",
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = BlueBackground
                                    )
                                    Text(
                                        text = "Revisa y edita tus datos personales, como tu email y nombre de usuario.",
                                        fontSize = 14.sp,
                                        color = Color.Gray
                                    )
                                }
                            }
                        }

                        // Mostrar las opciones de "Datos del Usuario" si está expandido
                        if (isUserDataExpanded) {
                            AccountInfoCard(
                                title = "E-mail",
                                initialValue = "daianieves10@gmail.com",
                                isVerified = true
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            AccountInfoCard(
                                title = "Teléfono",
                                initialValue = "+524493134220",
                                isVerified = true
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            AccountInfoCard(
                                title = "Nombre de usuario",
                                initialValue = "NIEVESDAIÁN20230403051727",
                                isVerified = false
                            )
                        }

                        // Texto clickeable para Direcciones
                        TextButton(
                            onClick = {
                                isAddressDataExpanded = !isAddressDataExpanded
                                // Si se abre la dirección, se cierra los datos del usuario
                                if (isUserDataExpanded) isUserDataExpanded = false
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent) // Sin fondo
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Filled.Home,
                                    contentDescription = "Direcciones",
                                    tint = BlueBackground,
                                    modifier = Modifier.size(40.dp) // Aumentamos el tamaño del ícono
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Column(
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Direcciones",
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = BlueBackground
                                    )
                                    Text(
                                        text = "Gestiona tus direcciones de envío o facturación.",
                                        fontSize = 14.sp,
                                        color = Color.Gray
                                    )
                                }
                            }
                        }

                        // Mostrar las opciones de "Direcciones" si está expandido
                        if (isAddressDataExpanded) {
                            Spacer(modifier = Modifier.height(8.dp))
                            AccountInfoCard(
                                title = "Código postal",
                                initialValue = "12345",
                                isVerified = false
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            AccountInfoCard(
                                title = "Estado",
                                initialValue = "Tu estado",
                                isVerified = false
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            AccountInfoCard(
                                title = "Municipio/Alcaldía",
                                initialValue = "Tu municipio",
                                isVerified = false
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            AccountInfoCard(
                                title = "Colonia",
                                initialValue = "Tu colonia",
                                isVerified = false
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            AccountInfoCard(
                                title = "Calle",
                                initialValue = "Tu calle",
                                isVerified = false
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AccountInfoCard(title: String, initialValue: String, isVerified: Boolean) {
    var isEditing by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf(initialValue) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp) // Elevación para sombra
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = BlueBackground
                )
                if (isVerified) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = "Validado",
                        tint = BlueBackground
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            if (isEditing) {
                OutlinedTextField(
                    value = value,
                    onValueChange = { value = it },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    label = { Text("Modificar $title") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { isEditing = false },
                    colors = ButtonDefaults.buttonColors(containerColor = BlueBackground),
                    elevation = ButtonDefaults.elevatedButtonElevation(4.dp) // Sombra añadida al botón
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
                            color = BlueBackground,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
