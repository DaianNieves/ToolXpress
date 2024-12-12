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
import androidx.compose.runtime.LaunchedEffect
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
import com.example.toolxpress.ui.components.TopBar
import com.example.toolxpress.ui.theme.BlueBackground
import com.example.toolxpress.ui.theme.YellowIcons

@Composable
fun DataUserScreen(navController: NavController) {
    var isUserDataExpanded by remember { mutableStateOf(false) }
    var isAddressDataExpanded by remember { mutableStateOf(false) }

    // Estado para los errores de los campos
    var emailError by remember { mutableStateOf<String?>(null) }
    var phoneError by remember { mutableStateOf<String?>(null) }
    var usernameError by remember { mutableStateOf<String?>(null) }
    var postalCodeError by remember { mutableStateOf<String?>(null) }
    var stateError by remember { mutableStateOf<String?>(null) }
    var cityError by remember { mutableStateOf<String?>(null) }
    var colonyError by remember { mutableStateOf<String?>(null) }
    var streetError by remember { mutableStateOf<String?>(null) }

    var emailValue by remember { mutableStateOf("daianieves10@gmail.com") }
    var phoneValue by remember { mutableStateOf("+524493134220") }
    var usernameValue by remember { mutableStateOf("NIEVESDAIÁN20230403051727") }
    var postalCodeValue by remember { mutableStateOf("12345") }
    var stateValue by remember { mutableStateOf("Tu estado") }
    var cityValue by remember { mutableStateOf("Tu municipio") }
    var colonyValue by remember { mutableStateOf("Tu colonia") }
    var streetValue by remember { mutableStateOf("Tu calle") }

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
                                initialValue = emailValue,
                                isVerified = true,
                                error = emailError,
                                onValueChange = { newValue ->
                                    emailValue = newValue
                                    emailError = validateField(newValue, "email")
                                }
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            AccountInfoCard(
                                title = "Teléfono",
                                initialValue = phoneValue,
                                isVerified = true,
                                error = phoneError,
                                onValueChange = { newValue ->
                                    phoneValue = newValue
                                    phoneError = validateField(newValue, "phone")
                                }
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            AccountInfoCard(
                                title = "Nombre de usuario",
                                initialValue = usernameValue,
                                isVerified = false,
                                error = usernameError,
                                onValueChange = { newValue ->
                                    usernameValue = newValue
                                    usernameError = validateField(newValue, "username")
                                }
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
                                initialValue = postalCodeValue,
                                isVerified = false,
                                error = postalCodeError,
                                onValueChange = { newValue ->
                                    postalCodeValue = newValue
                                    postalCodeError = validateField(newValue, "postalCode")
                                }
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            AccountInfoCard(
                                title = "Estado",
                                initialValue = stateValue,
                                isVerified = false,
                                error = stateError,
                                onValueChange = { newValue ->
                                    stateValue = newValue
                                    stateError = validateField(newValue, "state")
                                }
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            AccountInfoCard(
                                title = "Municipio/Alcaldía",
                                initialValue = cityValue,
                                isVerified = false,
                                error = cityError,
                                onValueChange = { newValue ->
                                    cityValue = newValue
                                    cityError = validateField(newValue, "city")
                                }
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            AccountInfoCard(
                                title = "Colonia",
                                initialValue = colonyValue,
                                isVerified = false,
                                error = colonyError,
                                onValueChange = { newValue ->
                                    colonyValue = newValue
                                    colonyError = validateField(newValue, "colony")
                                }
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            AccountInfoCard(
                                title = "Calle",
                                initialValue = streetValue,
                                isVerified = false,
                                error = streetError,
                                onValueChange = { newValue ->
                                    streetValue = newValue
                                    streetError = validateField(newValue, "street")
                                }
                            )
                        }
                    }
                }

                // Botón para guardar
                Button(
                    onClick = {
                        // Validar todos los campos antes de guardar
                        emailError = validateField(emailValue, "email")
                        phoneError = validateField(phoneValue, "phone")
                        usernameError = validateField(usernameValue, "username")
                        postalCodeError = validateField(postalCodeValue, "postalCode")
                        stateError = validateField(stateValue, "state")
                        cityError = validateField(cityValue, "city")
                        colonyError = validateField(colonyValue, "colony")
                        streetError = validateField(streetValue, "street")

                        // Si no hay errores, guardar
                        if (listOf(
                                emailError,
                                phoneError,
                                usernameError,
                                postalCodeError,
                                stateError,
                                cityError,
                                colonyError,
                                streetError
                            ).all { it == null }
                        ) {
                            // Guardar la información
                            navController.navigate("StartScreen")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = YellowIcons),
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
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
fun AccountInfoCard(
    title: String,
    initialValue: String,
    isVerified: Boolean,
    error: String? = null,
    onValueChange: (String) -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf(initialValue) }

    // Se limpia el error cuando el usuario comienza a editar el campo
    LaunchedEffect(isEditing) {
        if (isEditing) {
            error?.let {
                // Borra el error solo cuando el campo es editado
                onValueChange("")
            }
        }
    }

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
                    onValueChange = {
                        value = it
                        onValueChange(it)  // Actualiza el valor desde el padre
                    },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    label = { Text("Modificar $title") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        isEditing = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = BlueBackground),
                    elevation = ButtonDefaults.elevatedButtonElevation(4.dp)
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
                        fontWeight = FontWeight.Normal,
                        color = if (error == null) Color.Black else BlueBackground
                    )
                    if (error != null) {
                        Text(
                            text = error,
                            fontSize = 12.sp,
                            color = BlueBackground,
                            fontWeight = FontWeight.Normal
                        )
                    }
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

fun validateField(value: String, fieldType: String): String? {
    return when (fieldType) {
        "email" -> {
            when {
                value.isEmpty() -> "El correo es obligatorio"
                !value.contains("@") -> "Debe contener '@'"
                !value.contains(".") -> "Incluir dominio"
                else -> null
            }
        }
        "phone" -> {
            when {
                value.isEmpty() -> "El teléfono es obligatorio"
                value.length < 10 -> "Mínimo 10 dígitos"
                else -> null
            }
        }
        "username" -> {
            when {
                value.isEmpty() -> "El nombre de usuario es obligatorio"
                value.length < 5 -> "Al menos 5 caracteres"
                value.contains(" ") -> "No debe tener espacios"
                else -> null
            }
        }
        "postalcode" -> {
            when {
                value.isEmpty() -> "El código postal es obligatorio"
                value.length != 5 -> "Debe tener 5 dígitos"
                !value.all { it.isDigit() } -> "Solo números"
                else -> null
            }
        }
        "state" -> {
            when {
                value.isEmpty() -> "El estado es obligatorio"
                value.length < 3 -> "Al menos 3 caracteres"
                else -> null
            }
        }
        "city" -> {
            when {
                value.isEmpty() -> "La ciudad es obligatoria"
                value.length < 3 -> "Debe tener al menos 3 caracteres"
                else -> null
            }
        }
        "colony" -> {
            when {
                value.isEmpty() -> "La colonia es obligatoria"
                value.length < 3 -> "Debe tener al menos 3 caracteres"
                else -> null
            }
        }
        "street" -> {
            when {
                value.isEmpty() -> "La calle es obligatoria"
                value.length < 3 -> "Debe tener al menos 3 caracteres"
                else -> null
            }
        }
        else -> null
    }
}