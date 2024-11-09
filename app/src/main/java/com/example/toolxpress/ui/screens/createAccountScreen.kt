import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toolxpress.R
import com.example.toolxpress.ui.theme.GrayProduct
import com.example.toolxpress.ui.theme.Orange
import com.example.toolxpress.ui.components.TopBar


@Composable
fun CreateAccountScreen(navController: NavController) {
    // Variables de estado
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var hasEmailError by remember { mutableStateOf(false) }
    var hasPasswordError by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        // TopBar permanece fijo, fuera del scroll
        TopBar(navController)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 40.dp)
        ) {
            // Imagen (Logo)
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally)
            )

            // Crear una cuenta
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp,
                            bottomStart = 16.dp,
                            bottomEnd = 16.dp
                        )
                    )
                    .background(GrayProduct)
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(), // El Box ocupará todo el espacio de la Row
                    contentAlignment = Alignment.Center // Alinea el contenido (texto) en el centro del Box
                ) {
                    Text(
                        text = "Create a New Account",
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            //Usuario
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Username",
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp, bottom = 6.dp)
                )
                Row(
                    modifier = Modifier
                        .background(
                            Color.LightGray.copy(alpha = 0.7f),
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(14.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Spacer(modifier = Modifier.width(10.dp))

                    BasicTextField(
                        value = username,
                        onValueChange = {
                            username = it.filter { char -> char != ' ' }
                            //hasPasswordError = password.isEmpty()
                        },
                        modifier = Modifier.weight(1f),
                        decorationBox = { innerTextField ->
                            Box(Modifier.fillMaxWidth()) {
                                if (username.isEmpty()) {
                                    Text(
                                        "Enter your Username",
                                        color = Color.Gray,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                innerTextField()
                            }
                        },
                        textStyle = LocalTextStyle.current.copy(
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        singleLine = true,  // input se mantenga en una sola línea
                        maxLines = 1
                    )
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            //Correo electrónico
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Email",
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp, bottom = 6.dp)
                )
                Row(
                    modifier = Modifier
                        .background(
                            Color.LightGray.copy(alpha = 0.7f),
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(14.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(10.dp))

                    BasicTextField(
                        value = email,
                        onValueChange = {
                            email = it.filter { char -> char != ' ' }
                            // Validar si el correo contiene '@' y tiene un formato adecuado
                            hasEmailError = !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                                .matches() && email.isNotEmpty()
                        },
                        modifier = Modifier.weight(1f),
                        decorationBox = { innerTextField ->
                            Box(Modifier.fillMaxWidth()) {
                                if (email.isEmpty()) {
                                    Text(
                                        "Enter your email address",
                                        color = Color.Gray,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                innerTextField()
                            }
                        },
                        textStyle = LocalTextStyle.current.copy(
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        singleLine = true,
                        maxLines = 1

                    )
                }// Mostrar mensaje de error si el correo no es válido
                if (hasEmailError) {
                    Text(
                        text = "Please enter a valid email address.",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            //Contraseña

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Password",
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp, bottom = 6.dp)
                )
                Row(
                    modifier = Modifier
                        .background(
                            Color.LightGray.copy(alpha = 0.7f),
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(14.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Spacer(modifier = Modifier.width(10.dp))

                    BasicTextField(
                        value = password,
                        onValueChange = {
                            password =  it.filter { char -> char != ' ' }
                            hasPasswordError = password.isEmpty()
                        },
                        modifier = Modifier.weight(1f),
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        decorationBox = { innerTextField ->
                            Box(Modifier.fillMaxWidth()) {
                                if (password.isEmpty()) {
                                    Text(
                                        "Enter your password",
                                        color = Color.Gray,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                innerTextField()
                            }
                        },
                        textStyle = LocalTextStyle.current.copy(
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = "Toggle Password Visibility",
                        modifier = Modifier
                            .size(28.dp)
                            .clickable { passwordVisible = !passwordVisible },
                        tint = Color.DarkGray
                    )
                }
                if (hasPasswordError) {
                    Text(
                        text = "Please enter your password.",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(60.dp))

            //Crear cuenta
            Button(
                onClick = {navController.navigate("login")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Orange),
                enabled = !hasEmailError && !hasPasswordError && email.isNotEmpty() && password.isNotEmpty() // Deshabilitar si hay errores o campos vacíos
            ) {
                Text(
                    text = "Create Account",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
