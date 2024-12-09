package com.example.toolxpress.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavController
import com.example.toolxpress.ui.theme.BlueBackground
import com.example.toolxpress.ui.theme.Orange
import com.example.toolxpress.ui.theme.YellowIcons

@Composable
fun DomicilioScreen(navController: NavController) {

    //Declaración de Variables
    var name by remember { mutableStateOf("") }
    var postalCode by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var colony by remember { mutableStateOf("") }
    var street by remember { mutableStateOf("") }
    var houseNumber by remember { mutableStateOf("") }
    var betweenStreet1 by remember { mutableStateOf("") }
    var betweenStreet2 by remember { mutableStateOf("") }
    var isHome by remember { mutableStateOf(true) }
    var contactNumber by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Barra Superior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 0.dp)
                .statusBarsPadding()
        ) {

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
                        navController.popBackStack() // Acción para regresar a la pantalla anterior
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Información Necesaria",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = BlueBackground
                )
            }
        }

        // Contenido Principal
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState) // Habilitar scroll vertical
                    .padding(16.dp)
            ) {

                TextFieldWithLabel(
                    label = "Nombre y apellido",
                    value = name,
                    onValueChange = { name = it },
                    placeholder = "Tal cual figure en el INE o IFE.",
                )


                // Espaciado uniforme
                Spacer(modifier = Modifier.height(12.dp))

                // Código postal (Validar que solo se ingresen números)
                TextFieldWithLabel(
                    label = "Código postal",
                    value = postalCode,
                    onValueChange = {
                        if (it.all { char -> char.isDigit() }) postalCode =
                            it // Validar que solo se ingresen números
                    },
                    placeholder = "Ingresa tu código postal",
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Estado
                TextFieldWithLabel(
                    label = "Estado",
                    value = state,
                    onValueChange = { state = it },
                    placeholder = "Ingresa tu estado"
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Municipio/Alcaldía
                TextFieldWithLabel(
                    label = "Municipio/Alcaldía",
                    value = city,
                    onValueChange = { city = it },
                    placeholder = "Ingresa tu municipio o alcaldía"
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Colonia
                TextFieldWithLabel(
                    label = "Colonia",
                    value = colony,
                    onValueChange = { colony = it },
                    placeholder = "Ingresa tu colonia"
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Calle
                TextFieldWithLabel(
                    label = "Calle",
                    value = street,
                    onValueChange = { street = it },
                    placeholder = "Escribe solo el nombre de la calle o avenida."
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Número (Validar que solo se ingresen números)
                TextFieldWithLabel(
                    label = "Número de casa",
                    value = houseNumber,
                    onValueChange = {
                        if (it.all { char -> char.isDigit() }) houseNumber =
                            it // Validar que solo se ingresen números
                    },
                    placeholder = "Ingresa el número"
                )

                Spacer(modifier = Modifier.height(12.dp))


                /* ¿Es tu trabajo o tu casa?
                Text(
                    text = "¿Es tu trabajo o tu casa?",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    RadioButton(
                        selected = isHome,
                        onClick = { isHome = true }
                    )
                    Text(text = "Casa", modifier = Modifier.padding(start = 8.dp))

                    Spacer(modifier = Modifier.width(16.dp))

                    RadioButton(
                        selected = !isHome,
                        onClick = { isHome = false }
                    )
                    Text(text = "Trabajo", modifier = Modifier.padding(start = 8.dp))
                }*/

                // Teléfono de contacto (Validar que solo se ingresen números)
                TextFieldWithLabel(
                    label = "Teléfono de contacto",
                    value = contactNumber,
                    onValueChange = {
                        if (it.all { char -> char.isDigit() }) contactNumber =
                            it // Validar que solo se ingresen números
                    },
                    placeholder = "Ingresa tu teléfono de contacto",
                    keyboardType = KeyboardType.Phone
                )

                Spacer(modifier = Modifier.height(24.dp)) // Espaciado antes del botón

                Box(
                    modifier = Modifier.fillMaxSize(), // El Box ocupa toda el área disponible
                    contentAlignment = Alignment.Center // Centra el contenido (el botón)
                ) {
                    Button(
                        onClick = {
                            navController.navigate("StartScreen")
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = YellowIcons),
                        modifier = Modifier.align(Alignment.Center) // Asegura que el botón esté centrado
                    ) {
                        Text(
                            text = "Guardar Información",
                            color = BlueBackground,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

@Composable
fun TextFieldWithLabel(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        if (label.isNotEmpty()) {
            Text(
                text = label,
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = BlueBackground) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),  // Ajuste para el tipo de teclado
            visualTransformation = visualTransformation,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        )
    }
}

