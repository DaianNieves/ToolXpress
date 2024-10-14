package com.example.toolxpress.ui.theme.screens

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toolxpress.R
import com.example.toolxpress.ui.theme.Orange
import com.example.toolxpress.ui.theme.Purple80

@Composable
fun MetodoPagoScreen(navController: NavController) {
    var selectedPaymentMethod by remember { mutableStateOf("") }
    var selectedCardType by remember { mutableStateOf("") }
    var cardNumber by remember { mutableStateOf("") }
    var cardExpiry by remember { mutableStateOf("") }
    var cardCVC by remember { mutableStateOf("") }
    var cardHolderName by remember { mutableStateOf("") }
    var paypalEmail by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        TopBar(navController, "Método de Pago")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(top = 72.dp)
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Selecciona un método de pago",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
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

            Spacer(modifier = Modifier.height(48.dp))

            when (selectedPaymentMethod) {
                "Tarjeta" -> {
                    Text(
                        text = "Selecciona el tipo de tarjeta",
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
                        label = { Text("Nombre del titular") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )
                    OutlinedTextField(
                        value = cardNumber,
                        onValueChange = { cardNumber = it },
                        label = { Text("Número de Tarjeta") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )
                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = cardExpiry,
                            onValueChange = { cardExpiry = it },
                            label = { Text("Fecha de Expiración") },
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp)
                        )
                        OutlinedTextField(
                            value = cardCVC,
                            onValueChange = { cardCVC = it },
                            label = { Text("CVC") },
                            modifier = Modifier
                                .weight(1f)
                        )
                    }
                }
                "PayPal" -> {
                    OutlinedTextField(
                        value = paypalEmail,
                        onValueChange = { paypalEmail = it },
                        label = { Text("Correo de PayPal") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {navController.navigate("EnvioScreen")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Orange)
            ) {
                Text(
                    text = "Confirmar Pago",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun TopBar(navController: NavController, title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Orange)
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Regresar",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.width(3.dp))

            Text(
                text = title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
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
                if (isSelected) Purple80 else Color.White,
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
                color = Color.Black
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
                if (isSelected) Purple80 else Color.White,
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
                color = Color.Black
            )
        )
    }
}
