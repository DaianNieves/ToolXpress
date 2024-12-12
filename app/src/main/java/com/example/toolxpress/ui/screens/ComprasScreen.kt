package com.example.toolxpress.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toolxpress.data.model.ShoppingCartViewModel
import com.example.toolxpress.data.model.ShoppingModel
import com.example.toolxpress.ui.components.TopBar
import com.example.toolxpress.ui.theme.BlueBackground
import com.example.toolxpress.ui.theme.GrayProduct
import com.example.toolxpress.ui.theme.GreenPrice
import com.example.toolxpress.ui.theme.YellowIcons

@Composable
fun ComprasScreen(navController: NavController, shoppingCartViewModel: ShoppingCartViewModel) {
    val purchaseHistory = shoppingCartViewModel.purchaseHistory

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(navController = navController)

        if (purchaseHistory.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "Historial de Compras",
                        modifier = Modifier.size(100.dp),
                        tint = YellowIcons // Color amarillo para el ícono
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "No tienes compras aún",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = YellowIcons // Texto de color amarillo
                    )
                }
            }
        } else {
            // Título de la pantalla
            Text(
                text = "Mis Compras",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = YellowIcons, // Color amarillo
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                textAlign = TextAlign.Center
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(purchaseHistory.size) { index ->
                    val purchase = purchaseHistory[index]
                    PurchaseItem(purchase = purchase)
                }
            }
        }
    }
}


@Composable
fun PurchaseItem(purchase: List<ShoppingModel>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Imagen del producto (primer producto de la compra como referencia)
                val firstProduct = purchase.firstOrNull()
                if (firstProduct != null) {
                    Image(
                        painter = painterResource(id = firstProduct.imageResId),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(100.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Información de la compra
                Column(modifier = Modifier.weight(1f)) {
                    val totalItems = purchase.sumOf { it.selectedQuantity }
                    val totalPrice = purchase.sumOf { (it.price.replace("$", "").toDoubleOrNull() ?: 0.0) * it.selectedQuantity }

                    Text(
                        text = "Compra #${purchase.hashCode()}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "Productos: $totalItems",
                        fontSize = 16.sp,
                        color = GrayProduct
                    )
                    Text(
                        text = "Total: $${"%.2f".format(totalPrice)}",
                        fontSize = 16.sp,
                        color = GreenPrice,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Divider para separar la lista de productos
            Divider(modifier = Modifier.padding(vertical = 8.dp))

            // Lista de productos de la compra
            purchase.forEach { product ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Nombre del producto
                    Text(
                        text = product.name,
                        modifier = Modifier.weight(1f),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    // Cantidad y precio
                    Text(
                        text = "x${product.selectedQuantity}",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                    Text(
                        text = "$${"%.2f".format((product.price.replace("$", "").toDoubleOrNull() ?: 0.0) * product.selectedQuantity)}",
                        fontSize = 14.sp,
                        color = BlueBackground
                    )
                }
            }
        }
    }
}
