package com.example.toolxpress

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.toolxpress.data.model.ShoppingCartViewModel
import com.example.toolxpress.notifications.NotificationManagerSingleton
import com.example.toolxpress.ui.components.ProductDataProvider
import com.example.toolxpress.ui.screens.CardProducts
import com.example.toolxpress.ui.screens.ComprasScreen
import com.example.toolxpress.ui.screens.CreateAccountScreen
import com.example.toolxpress.ui.screens.DataUserScreen
import com.example.toolxpress.ui.screens.DomicilioScreen
import com.example.toolxpress.ui.screens.EnvioScreen
import com.example.toolxpress.ui.screens.EstableDomicilioScreen
import com.example.toolxpress.ui.screens.LoginScreenP
import com.example.toolxpress.ui.screens.MainScreen
import com.example.toolxpress.ui.screens.MetodoPagoScreen
import com.example.toolxpress.ui.screens.ProductsScreen
import com.example.toolxpress.ui.screens.ShoppingCartScreen
import com.example.toolxpress.ui.theme.BlueBackground

class MainActivity : ComponentActivity() {
    private var onActivityResultListener: ((Int, Int, Intent?) -> Unit)? = null

    fun onActivityResultListener(listener: (Int, Int, Intent?) -> Unit) {
        onActivityResultListener = listener
    }

    fun removeOnActivityResultListener(listener: (Int, Int, Intent?) -> Unit) {
        if (onActivityResultListener == listener) onActivityResultListener = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        onActivityResultListener?.invoke(requestCode, resultCode, data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar el gestor global de notificaciones
        NotificationManagerSingleton.initialize(this)

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
    // Proporcionamos el ShoppingCartViewModel en el NavGraph
    val shoppingCartViewModel: ShoppingCartViewModel = viewModel()

    ProductDataProvider { allCategories -> // Obtener todas las categorías y productos
        NavHost(navController = navController, startDestination = "StartScreen") {
            composable("StartScreen") { MainScreen(navController, allCategories) }
            composable("login") { LoginScreenP(navController) }
            composable("createAccount") { CreateAccountScreen(navController) }
            composable("ShoppingCart") { ShoppingCartScreen(navController, shoppingCartViewModel) }
            composable("DomicilioScreen") { DomicilioScreen(navController) }
            composable("CardProducts/{productId}") { backStackEntry ->
                val productId = backStackEntry.arguments?.getString("productId")?.toInt() ?: 0
                // Pasa el ShoppingCartViewModel junto con el productId
                CardProducts(navController, productId, shoppingCartViewModel)
            }
            composable("EnvioScreen") { EnvioScreen(navController) }
            composable("MetodoPagoScreen") { MetodoPagoScreen(navController) }
            composable("ComprasScreen") { ComprasScreen(navController) }
            composable("ProductsScreen/{categoryName}") { backStackEntry ->
                val categoryName = backStackEntry.arguments?.getString("categoryName")
                ProductsScreen(navController, categoryName, allCategories) // Pasa todas las categorías aquí
            }
            composable("ProductsScreen") {
                ProductsScreen(navController, null, allCategories) // Pasa todas las categorías sin filtro
            }
            composable("DataUserScreen") { DataUserScreen(navController) }
            composable("EstableDomicilioScreen") { EstableDomicilioScreen(navController) }
        }
    }
}