package com.example.toolxpress.ui.screens

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toolxpress.R
import com.example.toolxpress.payments.CheckoutActivity
import com.example.toolxpress.ui.theme.BlueBackground
import com.example.toolxpress.ui.theme.Orange
import com.example.toolxpress.ui.theme.Purple80
import com.example.toolxpress.ui.theme.YellowIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MetodoPagoScreen(navController: NavController) {
    val context = LocalContext.current
    var selectedPaymentMethod by remember { mutableStateOf("") }
    var selectedCardType by remember { mutableStateOf("") }
    var cardNumber by remember { mutableStateOf("") }
    var cardExpiry by remember { mutableStateOf("") }
    var cardCVC by remember { mutableStateOf("") }
    var cardHolderName by remember { mutableStateOf("") }
    var paypalEmail by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    // Lanzador para iniciar `CheckoutActivity` y recibir el resultado
    val checkoutLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // Navegar a `EnvioScreen` si el pago fue exitoso
            navController.navigate("EnvioScreen")
        }
    }

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
                    text = "Método de Pago",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = BlueBackground
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            Text(
                text = "Selecciona un método de pago",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = YellowIcons
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            PaymentOptionItem(
                icon = painterResource(id = R.drawable.tarjeta),
                text = "Tarjeta de Crédito/Débito",
                isSelected = selectedPaymentMethod == "Tarjeta",
                onClick = { selectedPaymentMethod = "Tarjeta" }
            )

            Spacer(modifier = Modifier.height(32.dp))

            PaymentOptionItem(
                icon = painterResource(id = R.drawable.paypal),
                text = "PayPal",
                isSelected = selectedPaymentMethod == "PayPal",
                onClick = { selectedPaymentMethod = "PayPal" }
            )

            Spacer(modifier = Modifier.height(32.dp))

            PaymentOptionItem(
                icon = painterResource(id = R.drawable.google_pay),
                text = "GooglePay",
                isSelected = selectedPaymentMethod == "GooglePay",
                onClick = {
                    selectedPaymentMethod = "GooglePay" }
            )

            Spacer(modifier = Modifier.height(48.dp))

            when (selectedPaymentMethod) {
                "Tarjeta" -> {
                    Text(
                        text = "Selecciona el tipo de tarjeta",
                        color = YellowIcons,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Row(modifier = Modifier.fillMaxWidth()) {
                        SubOptionItemWithImage(
                            image = painterResource(id = R.drawable.visa),
                            text = "Visa",
                            isSelected = selectedCardType == "Visa",
                            onClick = { selectedCardType = "Visa" }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        SubOptionItemWithImage(
                            image = painterResource(id = R.drawable.mastercard),
                            text = "MasterCard",
                            isSelected = selectedCardType == "MasterCard",
                            onClick = { selectedCardType = "MasterCard" }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = cardHolderName,
                        onValueChange = { cardHolderName = it },
                        label = { Text("Nombre del titular", color = Color.White) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedTextColor = Color.White,                // Cambiar el color del texto
                            focusedBorderColor = YellowIcons,      // Cambiar el color del borde cuando está enfocado
                            unfocusedBorderColor = Color.White,     // Cambiar el color del borde cuando no está enfocado
                            cursorColor = YellowIcons              // Cambiar el color del cursor
                        )
                    )

                    OutlinedTextField(
                        value = cardNumber,
                        onValueChange = { cardNumber = it },
                        label = { Text("Número de Tarjeta", color = Color.White) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedTextColor = Color.White,                // Cambiar el color del texto
                            focusedBorderColor = YellowIcons,      // Cambiar el color del borde cuando está enfocado
                            unfocusedBorderColor = Color.White,     // Cambiar el color del borde cuando no está enfocado
                            cursorColor = YellowIcons              // Cambiar el color del cursor
                        )
                    )
                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = cardExpiry,
                            onValueChange = { cardExpiry = it },
                            label = { Text("Fecha de Expiración", color = Color.White) },
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedTextColor = Color.White,                // Cambiar el color del texto
                                focusedBorderColor = YellowIcons,      // Cambiar el color del borde cuando está enfocado
                                unfocusedBorderColor = Color.White,     // Cambiar el color del borde cuando no está enfocado
                                cursorColor = YellowIcons
                            )
                        )
                        OutlinedTextField(
                            value = cardCVC,
                            onValueChange = { cardCVC = it },
                            label = { Text("CVC", color = Color.White) },
                            modifier = Modifier
                                .weight(1f),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedTextColor = Color.White,                // Cambiar el color del texto
                                focusedBorderColor = YellowIcons,      // Cambiar el color del borde cuando está enfocado
                                unfocusedBorderColor = Color.White,     // Cambiar el color del borde cuando no está enfocado
                                cursorColor = YellowIcons
                            )
                        )
                    }
                }

                "PayPal" -> {
                    OutlinedTextField(
                        value = paypalEmail,
                        onValueChange = { paypalEmail = it },
                        label = { Text("Correo de PayPal", color = Color.White) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedTextColor = Color.White,                // Cambiar el color del texto
                            focusedBorderColor = YellowIcons,      // Cambiar el color del borde cuando está enfocado
                            unfocusedBorderColor = Color.White,     // Cambiar el color del borde cuando no está enfocado
                            cursorColor = YellowIcons
                        )
                    )
                }
            }
                Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (selectedPaymentMethod == "GooglePay") {
                        val intent = Intent(context, CheckoutActivity::class.java)
                        checkoutLauncher.launch(intent)
                    } else {
                        navController.navigate("EnvioScreen") // Si no es Google Pay, navega directamente
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = YellowIcons)
            ) {
                Text(
                    text = "Confirmar Pago",
                    color = BlueBackground,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Composable
fun PaymentOptionItem(
    icon: Painter,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (isSelected) YellowIcons else Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick)
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = text,
            modifier = Modifier.size(48.dp),
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                color = BlueBackground
            ),
            fontSize = 18.sp
        )
    }
}

@Composable
fun SubOptionItemWithImage(
    image: Painter,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(
                if (isSelected) YellowIcons else Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = image,
            contentDescription = text,
            modifier = Modifier.size(48.dp),
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                color = BlueBackground
            )
        )
    }
}
