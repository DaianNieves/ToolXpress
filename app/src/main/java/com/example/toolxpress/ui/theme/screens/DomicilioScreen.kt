package com.example.toolxpress.ui.theme.screens

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.toolxpress.ui.theme.Orange

@Composable
fun EnvioScreen(navController: NavController) {

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

    // Scroll state
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState) // Habilitar scroll vertical
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(100.dp))
        // Header
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
                tint = Color.Black,
                modifier = Modifier.clickable {
                    navController.popBackStack() // Acción para regresar a la pantalla anterior
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Agrega un domicilio",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nombre y apellido
        TextFieldWithLabel(
            label = "Nombre y apellido",
            value = name,
            onValueChange = { name = it },
            placeholder = "Tal cual figure en el INE o IFE."
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Código postal (Validar que solo se ingresen números)
        TextFieldWithLabel(
            label = "Código postal",
            value = postalCode,
            onValueChange = {
                if (it.all { char -> char.isDigit() }) postalCode = it // Validar que solo se ingresen números
            },
            placeholder = "Ingresa tu código postal",
            keyboardType = KeyboardType.Number
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Estado
        TextFieldWithLabel(
            label = "Estado",
            value = state,
            onValueChange = { state = it },
            placeholder = "Ingresa tu estado"
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Municipio/Alcaldía
        TextFieldWithLabel(
            label = "Municipio/Alcaldía",
            value = city,
            onValueChange = { city = it },
            placeholder = "Ingresa tu municipio o alcaldía"
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Colonia
        TextFieldWithLabel(
            label = "Colonia",
            value = colony,
            onValueChange = { colony = it },
            placeholder = "Ingresa tu colonia"
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Calle
        TextFieldWithLabel(
            label = "Calle",
            value = street,
            onValueChange = { street = it },
            placeholder = "Escribe solo el nombre de la calle o avenida."
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Número (Validar que solo se ingresen números)
        TextFieldWithLabel(
            label = "Número",
            value = houseNumber,
            onValueChange = {
                if (it.all { char -> char.isDigit() }) houseNumber = it // Validar que solo se ingresen números
            },
            placeholder = "Ingresa el número"
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Entre qué calles está
        TextFieldWithLabel(
            label = "¿Entre qué calles está?",
            value = betweenStreet1,
            onValueChange = { betweenStreet1 = it },
            placeholder = "Calle 1"
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextFieldWithLabel(
            label = "",
            value = betweenStreet2,
            onValueChange = { betweenStreet2 = it },
            placeholder = "Calle 2"
        )

        Spacer(modifier = Modifier.height(8.dp))

        // ¿Es tu trabajo o tu casa?
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
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Teléfono de contacto (Validar que solo se ingresen números)
        TextFieldWithLabel(
            label = "Teléfono de contacto",
            value = contactNumber,
            onValueChange = {
                if (it.all { char -> char.isDigit() }) contactNumber = it // Validar que solo se ingresen números
            },
            placeholder = "Ingresa tu teléfono de contacto",
            keyboardType = KeyboardType.Phone
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botón de guardar
        Button(
            onClick = {
                // Acción al guardar el domicilio
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Orange)
        ) {
            Text(
                text = "Guardar domicilio",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
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
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),  // Ajuste para el tipo de teclado
            visualTransformation = visualTransformation,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        )
    }
}

