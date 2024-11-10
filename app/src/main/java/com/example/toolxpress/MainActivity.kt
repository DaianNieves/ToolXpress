package com.example.toolxpress

import CreateAccountScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.toolxpress.ui.components.ProductDataProvider
import com.example.toolxpress.ui.screens.CardProducts
import com.example.toolxpress.ui.screens.ComprasScreen
import com.example.toolxpress.ui.screens.DataUserScreen
import com.example.toolxpress.ui.screens.DomicilioScreen
import com.example.toolxpress.ui.screens.EnvioScreen
import com.example.toolxpress.ui.screens.LoginScreenP
import com.example.toolxpress.ui.screens.MainScreen
import com.example.toolxpress.ui.screens.MetodoPagoScreen
import com.example.toolxpress.ui.screens.ProductsScreen
import com.example.toolxpress.ui.screens.ShoppingCartScreen
import com.example.toolxpress.ui.theme.BlueBackground

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposableMultiScreenApp()
        }
    }
}

@Composable
fun ComposableMultiScreenApp() {
    val navController = rememberNavController()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BlueBackground // Fondo azul marino
    ) {
        SetupNavGraph(navController = navController)
    }
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    ProductDataProvider { allCategories ->
        NavHost(navController = navController, startDestination = "StartScreen") {
            composable("StartScreen") { MainScreen(navController, allCategories) }
            composable("login") { LoginScreenP(navController) }
            composable("createAccount") { CreateAccountScreen(navController) }
            composable("ShoppingCart") { ShoppingCartScreen(navController) }
            composable("DomicilioScreen") { DomicilioScreen(navController) }
            composable("CardProducts") { CardProducts(navController) }
            composable("EnvioScreen") { EnvioScreen(navController) }
            composable("MetodoPagoScreen") { MetodoPagoScreen(navController) } // Aquí está registrado
            composable("ComprasScreen") { ComprasScreen(navController) }
            composable("ProductsScreen/{categoryName}") { backStackEntry ->
                val categoryName = backStackEntry.arguments?.getString("categoryName")
                ProductsScreen(navController, categoryName, allCategories)
            }
            composable("ProductsScreen") { // Ruta para acceder a todos los productos
                ProductsScreen(navController, null, allCategories) // Pasar null para todos los productos
            }
            composable("DataUserScreen") { DataUserScreen(navController) }
        }
    }
}