package com.example.toolxpress

import CreateAccountScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.toolxpress.ui.theme.components.ProductDataProvider
import com.example.toolxpress.ui.theme.screens.CardProducts
import com.example.toolxpress.ui.theme.screens.DomicilioScreen
import com.example.toolxpress.ui.theme.screens.EnvioScreen
import com.example.toolxpress.ui.theme.screens.LoginScreenP
import com.example.toolxpress.ui.theme.screens.MainScreen
import com.example.toolxpress.ui.theme.screens.MetodoPagoScreen
import com.example.toolxpress.ui.theme.screens.ProductScreen
import com.example.toolxpress.ui.theme.screens.ProductsScreen
import com.example.toolxpress.ui.theme.screens.ShoppingCartScreen
import com.example.toolxpress.ui.theme.screens.StartScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposableMultiScreenApp()
            /*ToolXpressTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }*/
        }
    }
}

/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ToolXpressTheme {
        Greeting("Android")
    }
}
*/

@Composable
fun ComposableMultiScreenApp() {
    val navController = rememberNavController()
    Surface(color = Color.White) {
        SetupNavGraph(navController = navController)
    }
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    ProductDataProvider { allCategories ->
        NavHost(navController = navController, startDestination = "CardProducts") {
            composable("StartScreen") { MainScreen(navController, allCategories) }
            composable("login") { LoginScreenP(navController) }
            composable("createAccount") { CreateAccountScreen(navController) }
            composable("ShoppingCart") { ShoppingCartScreen(navController) }
            composable("DomicilioScreen") { DomicilioScreen(navController) }
            composable("CardProducts") { CardProducts(navController) }
            composable("EnvioScreen") { EnvioScreen(navController) }
            composable("MetodoPagoScreen") { MetodoPagoScreen(navController) }
            composable("ProductsScreen/{categoryName}") { backStackEntry ->
                val categoryName = backStackEntry.arguments?.getString("categoryName")
                ProductsScreen(navController, categoryName, allCategories)
            }
            composable("ProductsScreen") { // Ruta para acceder a todos los productos
                ProductsScreen(navController, null, allCategories) // Pasar null para todos los productos
            }
        }
    }
}









