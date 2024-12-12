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
    // Declaración de Variables
    var name by remember { mutableStateOf("") }
    var postalCode by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var colony by remember { mutableStateOf("") }
    var street by remember { mutableStateOf("") }
    var houseNumber by remember { mutableStateOf("") }
    var contactNumber by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    // Variables para errores
    var nameError by remember { mutableStateOf<String?>(null) }
    var postalCodeError by remember { mutableStateOf<String?>(null) }
    var stateError by remember { mutableStateOf<String?>(null) }
    var cityError by remember { mutableStateOf<String?>(null) }
    var colonyError by remember { mutableStateOf<String?>(null) }
    var streetError by remember { mutableStateOf<String?>(null) }
    var houseNumberError by remember { mutableStateOf<String?>(null) }
    var contactNumberError by remember { mutableStateOf<String?>(null) }

    // Función de validación por campo
    fun validateField(field: String, type: String): String? {
        return when (type) {
            "name" -> if (field.isBlank()) "El nombre no puede estar vacío." else null
            "postalCode" -> if (field.isBlank()) {
                "El código postal no puede estar vacío."
            } else if (field.length != 5 || field.any { !it.isDigit() }) {
                "Debe contener 5 números."
            } else null
            "state" -> if (field.isBlank()) "El estado no puede estar vacío." else null
            "city" -> if (field.isBlank()) "El municipio o alcaldía no puede estar vacío." else null
            "colony" -> if (field.isBlank()) "La colonia no puede estar vacía." else null
            "street" -> if (field.isBlank()) "La calle no puede estar vacía." else null
            "houseNumber" -> if (field.isBlank()) {
                "El número no puede estar vacío."
            } else if (field.any { !it.isDigit() }) {
                "Solo se permiten números."
            } else null
            "betweenStreet1" -> if (field.isBlank()) "Este campo no puede estar vacío." else null
            "betweenStreet2" -> if (field.isBlank()) "Este campo no puede estar vacío." else null
            "contactNumber" -> if (field.isBlank()) {
                "El teléfono no puede estar vacío."
            } else if (field.length != 10 || field.any { !it.isDigit() }) {
                "Debe contener 10 dígitos."
            } else null
            else -> null
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Barra Superior
        Box(
            modifier = Modifier
                .fillMaxWidth()
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
                        navController.popBackStack()
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            // Campos con validación inmediata al cambiar
            TextFieldWithValidation(
                label = "Nombre y apellido",
                value = name,
                onValueChange = {
                    name = it
                    nameError = validateField(it, "name")
                },
                error = nameError,
                placeholder = "Tal cual figure en el INE o IFE."
            )

            TextFieldWithValidation(
                label = "Código postal",
                value = postalCode,
                onValueChange = {
                    postalCode = it
                    postalCodeError = validateField(it, "postalCode")
                },
                error = postalCodeError,
                placeholder = "Ingresa tu código postal",
                keyboardType = KeyboardType.Number
            )

            TextFieldWithValidation(
                label = "Estado",
                value = state,
                onValueChange = {
                    state = it
                    stateError = validateField(it, "state")
                },
                error = stateError,
                placeholder = "Ingresa tu estado"
            )

            TextFieldWithValidation(
                label = "Municipio/Alcaldía",
                value = city,
                onValueChange = {
                    city = it
                    cityError = validateField(it, "city")
                },
                error = cityError,
                placeholder = "Ingresa tu municipio o alcaldía"
            )

            TextFieldWithValidation(
                label = "Colonia",
                value = colony,
                onValueChange = {
                    colony = it
                    colonyError = validateField(it, "colony")
                },
                error = colonyError,
                placeholder = "Ingresa tu colonia"
            )

            TextFieldWithValidation(
                label = "Calle",
                value = street,
                onValueChange = {
                    street = it
                    streetError = validateField(it, "street")
                },
                error = streetError,
                placeholder = "Escribe el nombre de la calle."
            )

            TextFieldWithValidation(
                label = "Número",
                value = houseNumber,
                onValueChange = {
                    houseNumber = it
                    houseNumberError = validateField(it, "houseNumber")
                },
                error = houseNumberError,
                placeholder = "Ingresa el número"
            )

            TextFieldWithValidation(
                label = "Teléfono de contacto",
                value = contactNumber,
                onValueChange = {
                    contactNumber = it
                    contactNumberError = validateField(it, "contactNumber")
                },
                error = contactNumberError,
                placeholder = "Ingresa tu teléfono de contacto",
                keyboardType = KeyboardType.Phone
            )

            // Botón Guardar
            Box(
                modifier = Modifier
                    .fillMaxWidth() // El Box ocupará todo el ancho disponible
                    .padding(horizontal = 16.dp), // Añadir un poco de padding
                contentAlignment = Alignment.Center // Centra el contenido dentro del Box
            ) {
                Button(
                    onClick = {
                        // Validar todos los campos al intentar guardar
                        nameError = validateField(name, "name")
                        postalCodeError = validateField(postalCode, "postalCode")
                        stateError = validateField(state, "state")
                        cityError = validateField(city, "city")
                        colonyError = validateField(colony, "colony")
                        streetError = validateField(street, "street")
                        houseNumberError = validateField(houseNumber, "houseNumber")
                        contactNumberError = validateField(contactNumber, "contactNumber")

                        // Si no hay errores, guardar
                        if (listOf(
                                nameError,
                                postalCodeError,
                                stateError,
                                cityError,
                                colonyError,
                                streetError,
                                houseNumberError,
                                contactNumberError
                            ).all { it == null }
                        ) {
                            navController.navigate("StartScreen")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = YellowIcons),
                    modifier = Modifier.fillMaxWidth(0.8f) // El botón ocupará un 80% del ancho de la pantalla
                ) {
                    Text(
                        text = "Guardar Información",
                        color = BlueBackground,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun TextFieldWithValidation(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    error: String? = null,
    placeholder: String = "",
    keyboardType: KeyboardType = KeyboardType.Text
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

        // TextField con validación
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = BlueBackground) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType), // Ajuste para el tipo de teclado
            isError = error != null, // Si hay un error, marcar el campo como "error"
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        )

        // Si hay error, mostrar el mensaje debajo del campo
        if (error != null) {
            Text(
                text = error,
                color = YellowIcons,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}