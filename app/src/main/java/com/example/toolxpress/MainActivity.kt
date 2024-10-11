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
import com.example.toolxpress.ui.theme.screens.DomicilioScreen
import com.example.toolxpress.ui.theme.screens.LoginScreenP
import com.example.toolxpress.ui.theme.screens.MainScreen
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
    NavHost(navController = navController, startDestination = " DomicilioScreen") {
        composable("login") { LoginScreenP(navController) }
        composable("createAccount") { CreateAccountScreen(navController) }
        composable("StartScreen") { MainScreen(navController) }
        composable(" DomicilioScreen") {  DomicilioScreen(navController) }

    }
}



