import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toolxpress.R

@Composable
fun CreateAccountScreen(navController: NavController) {
    // Variables de estado
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var hasEmailError by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 40.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Menú superior
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color.White,
                    modifier = Modifier.clickable {

                    }
                )
                Text(
                    text = "Categorías",
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = Color.White,
                    modifier = Modifier.clickable {

                    }
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Cart",
                    tint = Color.White,
                    modifier = Modifier.clickable {

                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Imagen (Logo)
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Crear una cuenta
            Text("Create a New Account", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))

            // nombre de usuario
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            // correo electrónico
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    // Validar correo contiene '@' y formato adecuado
                    hasEmailError = !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                        .matches() && email.isNotEmpty()
                },
                label = { Text("Email") },
                isError = hasEmailError
            )

            // Mostrar mensaje de error
            if (hasEmailError) {
                Text(
                    text = "Please enter a valid email address.",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 16.dp, top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // contraseña
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Botón para crear la cuenta
            Button(
                onClick = {
                    navController.popBackStack()
                },
                enabled = !hasEmailError && email.isNotEmpty() // El botón solo se habilita si....
            ) {
                Text("Create Account")
            }
        }

        // pie de página
        Footer(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun Footer(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF2C2C2C))
            .padding(vertical = 20.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Footer
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Primera columna
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    "TOOLXPRESS",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Los mejores productos a los mejores precios para todos tus proyectos de construcción y remodelación.",
                    color = Color.LightGray,
                    fontSize = 14.sp
                )
            }

            // Segunda columna (Enlaces útiles)
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "ENLACES ÚTILES",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Catálogo",
                    color = Color.LightGray,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable {
                        //navController.navigate("catalogScreen")
                    }
                )
                Text(
                    "Carrito",
                    color = Color.LightGray,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable {
                        //navController.navigate("cartScreen")
                    }
                )
                Text(
                    "Iniciar sesión",
                    color = Color.LightGray,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable {
                        //navController.navigate("loginScreen")
                    }
                )
            }

            // Tercera columna (Contacto)
            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                Text(
                    "CONTACTO",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "Phone",
                        tint = Color.LightGray,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "+1 234 567 890",
                        color = Color.LightGray,
                        fontSize = 14.sp,
                        modifier = Modifier.clickable {

                        }
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Email,
                        contentDescription = "Email",
                        tint = Color.LightGray,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "info@toolxpress.com",
                        color = Color.LightGray,
                        fontSize = 14.sp,
                        modifier = Modifier.clickable {

                        }
                    )
                }
                Text(
                    "Dirección: Calle Falsa 123, Ciudad, País",
                    color = Color.LightGray,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Divider(color = Color.DarkGray, thickness = 1.dp)

        // derechos de autor
        Text(
            "© 2024 TOOLXPRESS - Todos los derechos reservados.",
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}